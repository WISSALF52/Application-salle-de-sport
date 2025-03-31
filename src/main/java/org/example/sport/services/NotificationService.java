package org.example.sport.services;

import org.example.sport.entite.*;
import org.example.sport.repositories.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.sport.repositories.NotificationPaiementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationPaiementRepository notificationRepository;

    @Autowired
    private PaiementRepository paiementRepository;

    /**
     * Crée une nouvelle notification
     */
    @Transactional
    public NotificationPaiement creerNotification(Long idutilisateur, Long idpaiement,
                                                  Long idremboursement, String message, TypeNotification type) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdutilisateur(idutilisateur);

        NotificationPaiement notification = new NotificationPaiement();
        notification.setUtilisateur(utilisateur);

        if (idpaiement != null) {
            Paiement paiement = new Paiement();
            paiement.setIdpaiement(idpaiement); // Vérifier que l'entité Paiement a bien idpaiement en minuscule
            notification.setPaiement(paiement);
        }

        if (idremboursement != null) {
            Remboursement remboursement = new Remboursement();
            remboursement.setIdremboursement(idremboursement); // Vérifier que l'entité Remboursement a bien idremboursement en minuscule
            notification.setRemboursement(remboursement);
        }

        notification.setMessage(message);
        notification.setDateEnvoi(LocalDateTime.now());
        notification.setEstLu(false);
        notification.setType(type);

        return notificationRepository.save(notification);
    }

    /**
     * Marque une notification comme lue
     */
    @Transactional
    public NotificationPaiement marquerCommeLue(Long notificationId) {
        NotificationPaiement notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notification non trouvée"));

        notification.setEstLu(true);
        return notificationRepository.save(notification);
    }


}
