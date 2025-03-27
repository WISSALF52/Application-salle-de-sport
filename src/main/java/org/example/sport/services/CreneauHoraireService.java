package org.example.sport.services;

import org.example.sport.entite.CreneauHoraire;
import org.example.sport.repositories.CreneauHoraireRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreneauHoraireService {

    private final CreneauHoraireRepository creneauHoraireRepository;

    public CreneauHoraireService(CreneauHoraireRepository creneauHoraireRepository) {
        this.creneauHoraireRepository = creneauHoraireRepository;
    }

    // Ajouter un créneau
    public CreneauHoraire ajouterCreneau(CreneauHoraire creneau) {
        return creneauHoraireRepository.save(creneau);
    }

    // Obtenir tous les créneaux
    public List<CreneauHoraire> obtenirTousLesCreneaux() {
        return creneauHoraireRepository.findAll();
    }

    // Obtenir un créneau par ID
    public CreneauHoraire obtenirCreneauParId(Long id) {
        return creneauHoraireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Créneau non trouvé avec l'ID : " + id));
    }

    // Mettre à jour un créneau
    public CreneauHoraire mettreAJourCreneau(Long id, CreneauHoraire creneauModifie) {
        CreneauHoraire creneauExistant = obtenirCreneauParId(id);
        creneauExistant.setDateHeureDebut(creneauModifie.getDateHeureDebut());
        creneauExistant.setDateHeureFin(creneauModifie.getDateHeureFin());
        creneauExistant.setServiceSport(creneauModifie.getServiceSport());
        creneauExistant.setCours(creneauModifie.getCours());
        creneauExistant.setReservations(creneauModifie.getReservations());
        return creneauHoraireRepository.save(creneauExistant);
    }

    // Supprimer un créneau
    public void supprimerCreneau(Long id) {
        creneauHoraireRepository.deleteById(id);
    }
}
