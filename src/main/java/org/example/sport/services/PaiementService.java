package org.example.sport.services;

import org.example.sport.entite.*;
import org.example.sport.repositories.CodePromoRepository;
import org.example.sport.repositories.PaiementRepository;
import org.example.sport.repositories.RemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.sport.entite.Paiement;
import org.example.sport.entite.StatutPaiement;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;

    @Autowired
    private RemboursementRepository remboursementRepository;

    @Autowired
    private CodePromoRepository codePromoRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ServiceReservation reservationService; // Correction ici pour injecter un service, pas une entité

    /**
     * Crée un nouveau paiement pour une réservation
     */
    @Transactional
    public Paiement creerPaiement(Long utilisateurId, Long reservationId, BigDecimal montant, String codePromoStr) {
        Reservation reservation = reservationService.obtenirReservationParId(reservationId); // Assure-toi que la méthode existe dans ReservationService
        Client utilisateur = reservation.getUtilisateur();

        if (!utilisateur.getId().equals(utilisateurId)) {
            throw new IllegalArgumentException("L'utilisateur n'est pas autorisé à payer pour cette réservation");
        }

        // Vérification et application du code promo si présent
        CodePromo codePromo = null;
        if (codePromoStr != null && !codePromoStr.isEmpty()) {
            codePromo = verifierEtAppliquerCodePromo(codePromoStr, montant);
            if (codePromo != null) {
                montant = appliquerReduction(montant, codePromo);
            }
        }

        // Création du paiement
        Paiement paiement = new Paiement();
        paiement.setUtilisateur(utilisateur);
        paiement.setReservation(reservation);
        paiement.setMontant(montant);
        paiement.setDatePaiement(LocalDateTime.now());
        paiement.setStatut(StatutPaiement.EN_ATTENTE);
        paiement.setReferencePaiement("PAY-" + UUID.randomUUID().toString().substring(0, 8));
        paiement.setCodePromo(codePromo);

        // Enregistrement du paiement
        paiement = paiementRepository.save(paiement);

        // Mettre à jour le nombre d'utilisations du code promo si utilisé
        if (codePromo != null) {
            codePromo.setUtilisationsActuelles(codePromo.getUtilisationsActuelles() + 1);
            codePromoRepository.save(codePromo);

            // Envoyer une notification sur l'utilisation du code promo
            notificationService.creerNotification(
                    utilisateur.getId(),
                    paiement.getId(),
                    null,
                    "Code promo " + codePromo.getCode() + " appliqué avec succès.",
                    TypeNotification.CODE_PROMO_APPLIQUE
            );
        }

        return paiement;
    }

    /**
     * Confirme un paiement après traitement par un système de paiement externe
     */
    @Transactional
    public Paiement confirmerPaiement(String referencePaiement) {
        Paiement paiement = paiementRepository.findByReferencePaiement(referencePaiement)
                .orElseThrow(() -> new IllegalArgumentException("Paiement non trouvé"));

        if (paiement.getStatut() != StatutPaiement.EN_ATTENTE) {
            throw new IllegalStateException("Le paiement n'est pas en attente de confirmation");
        }

        // Mise à jour du statut du paiement
        paiement.setStatut(StatutPaiement.TERMINE);
        paiement = paiementRepository.save(paiement);

        // Mise à jour du statut de la réservation
        Reservation reservation = paiement.getReservation();
        reservation.setStatut(true);
        reservationService.mettreAJourReservation(reservation); // Assure-toi que cette méthode existe

        // Envoi d'une notification
        notificationService.creerNotification(
                paiement.getUtilisateur().getId(),
                paiement.getId(),
                null,
                "Votre paiement de " + paiement.getMontant() + "€ pour la réservation #" + reservation.getId() + " a été confirmé.",
                TypeNotification.PAIEMENT_REUSSI
        );

        return paiement;
    }

    /**
     * Crée un remboursement pour un paiement en cas d'annulation
     */
    @Transactional
    public Remboursement creerRemboursement(Long paiementId, String motif) {
        Paiement paiement = paiementRepository.findById(paiementId)
                .orElseThrow(() -> new IllegalArgumentException("Paiement non trouvé"));

        if (paiement.getStatut() != StatutPaiement.TERMINE) {
            throw new IllegalStateException("Le paiement n'est pas dans un état permettant le remboursement");
        }

        Reservation reservation = paiement.getReservation();

        // Calcul du montant de remboursement (peut varier selon les règles de l'entreprise)
        BigDecimal montantRemboursement = calculerMontantRemboursement(paiement, reservation);

        // Création du remboursement
        Remboursement remboursement = new Remboursement();
        remboursement.setPaiement(paiement);
        remboursement.setMontant(montantRemboursement);
        remboursement.setDateRemboursement(LocalDateTime.now());
        remboursement.setMotif(motif);
        remboursement.setStatut(StatutRemboursement.EN_COURS);
        remboursement.setReferenceRemboursement("RMB-" + UUID.randomUUID().toString().substring(0, 8));

        remboursement = remboursementRepository.save(remboursement);

        // Mise à jour du statut du paiement
        boolean remboursementTotal = montantRemboursement.compareTo(paiement.getMontant()) == 0;
        paiement.setStatut(remboursementTotal ? StatutPaiement.REMBOURSE_TOTALEMENT : StatutPaiement.REMBOURSE_PARTIELLEMENT);
        paiementRepository.save(paiement);

        // Envoi d'une notification
        notificationService.creerNotification(
                paiement.getUtilisateur().getId(),
                paiement.getId(),
                remboursement.getId(),
                "Votre demande de remboursement de " + montantRemboursement + "€ a été initiée. Motif: " + motif,
                TypeNotification.REMBOURSEMENT_INITIE
        );

        return remboursement;
    }

    /**
     * Confirme un remboursement après son traitement
     */
    @Transactional
    public Remboursement confirmerRemboursement(String referenceRemboursement) {
        Remboursement remboursement = remboursementRepository.findById(
                        Long.valueOf(referenceRemboursement.replace("RMB-", "")))
                .orElseThrow(() -> new IllegalArgumentException("Remboursement non trouvé"));

        if (remboursement.getStatut() != StatutRemboursement.EN_COURS) {
            throw new IllegalStateException("Le remboursement n'est pas en cours de traitement");
        }

        // Mise à jour du statut du remboursement
        remboursement.setStatut(StatutRemboursement.TERMINE);
        remboursement = remboursementRepository.save(remboursement);

        // Envoi d'une notification
        notificationService.creerNotification(
                remboursement.getPaiement().getUtilisateur().getId(),
                remboursement.getPaiement().getId(),
                remboursement.getId(),
                "Votre remboursement de " + remboursement.getMontant() + "€ a été effectué avec succès.",
                TypeNotification.REMBOURSEMENT_TERMINE
        );

        return remboursement;
    }

    // Méthodes privées pour appliquer la réduction et vérifier le code promo (pas de modifications nécessaires ici)
    private CodePromo verifierEtAppliquerCodePromo(String code, BigDecimal montant) {
        Optional<CodePromo> optCodePromo = codePromoRepository.findByCode(code);

        if (optCodePromo.isEmpty()) {
            throw new IllegalArgumentException("Code promo invalide");
        }

        CodePromo codePromo = optCodePromo.get();
        LocalDateTime maintenant = LocalDateTime.now();

        // Vérifications de validité
        if (!codePromo.isActif()) {
            throw new IllegalArgumentException("Ce code promo n'est plus actif");
        }

        if (maintenant.isBefore(codePromo.getDateDebut()) || maintenant.isAfter(codePromo.getDateExpiration())) {
            throw new IllegalArgumentException("Ce code promo a expiré ou n'est pas encore actif");
        }

        if (codePromo.getUtilisationsActuelles() >= codePromo.getUtilisationsMax()) {
            throw new IllegalArgumentException("Ce code promo a atteint son nombre maximum d'utilisations");
        }

        return codePromo;
    }

    private BigDecimal appliquerReduction(BigDecimal montant, CodePromo codePromo) {
        if (codePromo.isEstPourcentage()) {
            // Réduction en pourcentage
            BigDecimal reduction = montant.multiply(codePromo.getValeur()).divide(new BigDecimal(100));
            return montant.subtract(reduction);
        } else {
            // Réduction en montant fixe
            BigDecimal reduction = codePromo.getValeur();
            return montant.compareTo(reduction) > 0 ? montant.subtract(reduction) : BigDecimal.ZERO;
        }
    }

    private BigDecimal calculerMontantRemboursement(Paiement paiement, Reservation reservation) {
        BigDecimal montantOriginal = paiement.getMontant();
        LocalDateTime dateReservation = reservation.getDateReservation();
        LocalDateTime maintenant = LocalDateTime.now();

        // Exemple de règle: remboursement complet si annulé plus de 48h avant
        if (dateReservation.minusHours(48).isAfter(maintenant)) {
            return montantOriginal;
        }
        // Remboursement de 50% si annulé entre 24h et 48h avant
        else if (dateReservation.minusHours(24).isAfter(maintenant)) {
            return montantOriginal.multiply(new BigDecimal("0.5"));
        }
        // Aucun remboursement si annulé moins de 24h avant
        else {
            return BigDecimal.ZERO;
        }
    }
}
