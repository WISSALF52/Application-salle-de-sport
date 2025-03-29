package org.example.sport.repositories;

import org.example.sport.entite.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
    List<Cours> findByCategorie(String categorie);
}


