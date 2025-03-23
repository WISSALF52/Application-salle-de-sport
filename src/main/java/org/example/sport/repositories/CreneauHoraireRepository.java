package org.example.sport.repositories;

import org.example.sport.entite.creneauhoraire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreneauHoraireRepository extends JpaRepository<creneauhoraire, Long> {
    List<creneauhoraire> findByserviceId(Long serviceId);
}

