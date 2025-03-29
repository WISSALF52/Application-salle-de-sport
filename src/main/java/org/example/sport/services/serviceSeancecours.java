package org.example.sport.services;

import org.example.sport.entite.SeanceCours;
import org.example.sport.repositories.SeancecoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceSeancecours {

    @Autowired
    private SeancecoursRepository seanceCoursRepository;

    // Méthode pour planifier une séance
    public SeanceCours planifierSeance(SeanceCours seance) {
        return seanceCoursRepository.save(seance); // Utilisation correcte de l'objet injecté
    }
}
