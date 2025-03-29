package org.example.sport.repositories;
import org.example.sport.entite.Paiement;
import org.example.sport.entite.StatutPaiement;
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


}

