package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entite.NotificationPaiement;
import java.util.List;

@Repository
public interface NotificationPaiementRepository extends JpaRepository<NotificationPaiement, Long> {
    List<NotificationPaiement> findByUtilisateurIdAndEstLu(Long utilisateurId, boolean estLu);
    List<NotificationPaiement> findByPaiementId(Long paiementId);
    List<NotificationPaiement> findByRemboursementId(Long remboursementId);

    @Query("SELECT COUNT(n) FROM NotificationPaiement n WHERE n.utilisateur.id = :utilisateurId AND n.estLu = false")
    Long compterNotificationsNonLues(@Param("utilisateurId") Long utilisateurId);
}
