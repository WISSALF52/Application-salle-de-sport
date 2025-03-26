package com.example.demo.repositories;
import com.example.demo.entite.StatutRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entite.Remboursement;
import java.util.List;
@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByPaiementId(Long paiementId);
    List<Remboursement> findByStatut(StatutRemboursement statut);

   // @Query("SELECT r FROM Remboursement r WHERE r.paiement.utilisateur.id = :utilisateurId")
  //  List<Remboursement> trouverRemboursementsParUtilisateur(@Param("utilisateurId") Long utilisateurId);
}

