package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.example.demo.entite.CodePromo;
@Repository
public interface CodePromoRepository extends JpaRepository<CodePromo, Long> {
    Optional<CodePromo> findByCode(String code);
    List<CodePromo> findByActifAndDateExpirationAfter(boolean actif, LocalDateTime date);

    @Query("SELECT c FROM CodePromo c WHERE c.actif = true " +
            "AND c.dateDebut <= :maintenant " +
            "AND c.dateExpiration >= :maintenant " +
            "AND c.utilisationsActuelles < c.utilisationsMax")
    List<CodePromo> trouverCodesPromoValides(@Param("maintenant") LocalDateTime maintenant);
}
