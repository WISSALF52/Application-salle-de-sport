package com.example.demo.repositories;
//import com.reservation.sport.entite.*;
import com.example.demo.entite.Paiement;
import com.example.demo.entite.StatutPaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByUtilisateurId(Long utilisateurId);
    List<Paiement> findByReservationId(Long reservationId);
    List<Paiement> findByStatut(StatutPaiement statut);
    Optional<Paiement> findByReferencePaiement(String referencePaiement);

    @Query("SELECT p FROM Paiement p WHERE p.utilisateur.id = :utilisateurId AND p.datePaiement BETWEEN :debut AND :fin")
    List<Paiement> trouverPaiementsParUtilisateurEtPeriode(
            @Param("utilisateurId") Long utilisateurId,
            @Param("debut") LocalDateTime debut,
            @Param("fin") LocalDateTime fin);
}
