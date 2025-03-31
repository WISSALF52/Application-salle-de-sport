package org.example.sport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.example.sport.entite.CodePromo;
@Repository
public interface CodePromoRepository extends JpaRepository<CodePromo, Long> {
    Optional<Object> findByCode(String code);

    List<CodePromo> findByActifAndDateExpirationAfter(boolean b, LocalDateTime now);
}
