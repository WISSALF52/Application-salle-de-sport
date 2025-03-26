package com.example.demo.service;
import com.example.demo.entite.Remboursement;
import com.example.demo.entite.Paiement;
import com.example.demo.entite.TypeNotification;
import com.example.demo.repositories.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.repositories.NotificationPaiementRepository;
import java.time.LocalDateTime;
import java.util.List;
import com.example.demo.entite.NotificationPaiement;

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
    public NotificationPaiement creerNotification(Long utilisateurId, Long paiementId,
                                                  Long remboursementId, String message, TypeNotification type) {
      //  Utilisateur utilisateur = new Utilisateur();
       // utilisateur.setId(utilisateurId);

        NotificationPaiement notification = new NotificationPaiement();
        //notification.setUtilisateur(utilisateur);

        if (paiementId != null) {
            Paiement paiement = new Paiement();
            paiement.setId(paiementId);
            notification.setPaiement(paiement);
        }

        if (remboursementId != null) {
            Remboursement remboursement = new Remboursement();
            remboursement.setId(remboursementId);
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

    /**
     * Récupère toutes les notifications non lues d'un utilisateur
     */
    public List<NotificationPaiement> obtenirNotificationsNonLues(Long utilisateurId) {
        return notificationRepository.findByUtilisateurIdAndEstLu(utilisateurId, false);
    }
}
