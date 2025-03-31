package org.example.sport.repositories;

import org.example.sport.entite.StatutRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.sport.entite.Remboursement;
import java.util.List;
@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {

}
