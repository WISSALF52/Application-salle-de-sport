package org.example.sport.services;

import org.example.sport.entite.Client;
import org.example.sport.entite.Cours;
import org.example.sport.entite.SeanceCours;
import org.example.sport.repositories.CoursRepository;
import org.example.sport.repositories.UtilisateurRepository;
import org.example.sport.repositories.SeancecoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Servicecours {

    @Autowired
    private UtilisateurRepository membreRepository;

    @Autowired
    private SeancecoursRepository seancecoursRepository;

    @Autowired
    private final CoursRepository coursRepository;

    // Constructeur avec injection de dépendances pour CoursRepository
    public Servicecours(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    // Méthode pour inscrire un membre à une séance de cours
    public void inscrireAuCours(Long membreId, Long seanceId) {
        // Recherche du membre
        Client membre = (Client) membreRepository.findById(membreId)
                .orElseThrow(() -> new RuntimeException("Membre non trouvé"));

        // Recherche de la séance
        SeanceCours seance = seancecoursRepository.findById(seanceId)
                .orElseThrow(() -> new RuntimeException("Séance non trouvée"));

        // Vérification de la capacité
        List<Client> participants = seance.getParticipants();
        if (participants.size() < seance.getCours().getCapaciteMax()) {
            participants.add(membre);
            seancecoursRepository.save(seance);
        } else {
            throw new RuntimeException("Séance complète");
        }
    }

    // Méthode pour obtenir tous les cours
    public List<Cours> obtenirTousLesCours() {
        return coursRepository.findAll();
    }

    // Méthode pour obtenir tous les cours par catégorie
    public List<Cours> obtenirTousLesCours(String categorie) {
        return List.of();
    }}



