package org.example.sport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.sport.entite.NotificationPaiement;
import java.util.List;

@Repository
public interface NotificationPaiementRepository extends JpaRepository<NotificationPaiement, Long> {

}
