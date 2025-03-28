package org.example.sport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.sport.entite.Utilisateur;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
}