package org.example.sport.repositories;

import org.example.sport.entite.CreneauHoraire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreneauHoraireRepository extends JpaRepository<CreneauHoraire, Long> {
 // Utilisez le bon nom de propriété
}