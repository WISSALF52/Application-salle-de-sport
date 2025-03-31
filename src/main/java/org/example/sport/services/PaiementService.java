package org.example.sport.services;

import org.example.sport.entite.*;
import org.example.sport.repositories.CodePromoRepository;
import org.example.sport.repositories.PaiementRepository;
import org.example.sport.repositories.RemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private ServiceReservation reservationService;

    @Transactional
    public Paiement creerPaiement(Long utilisateurId, Long reservationId, BigDecimal montant, String codePromoStr) {
        Reservation reservation = reservationService.obtenirReservationParId(reservationId);
        Client utilisateur = reservation.getClient();

        if (!utilisateur.getIdutilisateur().equals(utilisateurId)) {
            throw new IllegalArgumentException("L'utilisateur n'est pas autorisé à payer pour cette réservation");
        }

        CodePromo codePromo = null;
        if (codePromoStr != null && !codePromoStr.isEmpty()) {
            codePromo = verifierEtAppliquerCodePromo(codePromoStr, montant);
            if (codePromo != null) {
                montant = appliquerReduction(montant, codePromo);
            }
        }

        Paiement paiement = new Paiement();
        paiement.setClient(utilisateur);
        paiement.setReservation(reservation);
        paiement.setMontant(montant);
        paiement.setDatePaiement(LocalDateTime.now());
        paiement.setStatut(StatutPaiement.EN_ATTENTE);
        paiement.setReferencePaiement("PAY-" + UUID.randomUUID().toString().substring(0, 8));
        paiement.setCodePromo(codePromo);

        paiement = paiementRepository.save(paiement);

        if (codePromo != null) {
            codePromo.setUtilisationsActuelles(codePromo.getUtilisationsActuelles() + 1);
            codePromoRepository.save(codePromo);
            notificationService.creerNotification(
                    utilisateur.getIdutilisateur(),
                    paiement.getIdpaiement(),
                    null,
                    "Code promo " + codePromo.getCode() + " appliqué avec succès.",
                    TypeNotification.CODE_PROMO_APPLIQUE
            );
        }

        return paiement;
    }

    private BigDecimal appliquerReduction(BigDecimal montant, CodePromo codePromo) {
        return montant;
    }

    private CodePromo verifierEtAppliquerCodePromo(String codePromoStr, BigDecimal montant) {
        return null;
    }

    @Transactional
    public Paiement confirmerPaiement(String referencePaiement) {
        Paiement paiement = (Paiement) paiementRepository.findByreferencePaiement(referencePaiement)
                .orElseThrow(() -> new IllegalArgumentException("Paiement non trouvé"));

        if (paiement.getStatut() != StatutPaiement.EN_ATTENTE) {
            throw new IllegalStateException("Le paiement n'est pas en attente de confirmation");
        }

        paiement.setStatut(StatutPaiement.TERMINE);
        paiement = paiementRepository.save(paiement);

        Reservation reservation = paiement.getReservation();
        reservation.setStatut(true);
        reservationService.mettreAJourReservation(reservation);

        notificationService.creerNotification(
                paiement.getClient().getIdutilisateur(),
                paiement.getIdpaiement(),
                null,
                "Votre paiement de " + paiement.getMontant() + "€ pour la réservation #" + reservation.getIdreservation() + " a été confirmé.",
                TypeNotification.PAIEMENT_REUSSI
        );

        return paiement;
    }

    public Remboursement creerRemboursement(Long idpaiement, String motif) {
        return null;
    }

    public Remboursement confirmerRemboursement(String referenceRemboursement) {

        return null;
    }}
