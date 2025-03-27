package org.example.sport.services;

import org.example.sport.entite.ServiceSport;
import org.example.sport.repositories.ServiceSportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSportService {

    private final ServiceSportRepository serviceSportRepository;

    public ServiceSportService(ServiceSportRepository serviceSportRepository) {
        this.serviceSportRepository = serviceSportRepository;
    }

    // Ajouter un service sportif
    public ServiceSport ajouterService(ServiceSport service) {
        return serviceSportRepository.save(service);
    }

    // Obtenir tous les services sportifs
    public List<ServiceSport> obtenirTousLesServices() {
        return serviceSportRepository.findAll();
    }

    // Obtenir un service sportif par ID
    public ServiceSport obtenirServiceParId(Long id) {
        return serviceSportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service sportif non trouvé avec l'ID : " + id));
    }

    // Mettre à jour un service sportif
    public ServiceSport mettreAJourService(Long id, ServiceSport serviceModifie) {
        ServiceSport serviceExistant = obtenirServiceParId(id);
        serviceExistant.setNom(serviceModifie.getNom());
        serviceExistant.setPrix(serviceModifie.getPrix());
        serviceExistant.setCapaciteMax(serviceModifie.getCapaciteMax());
        serviceExistant.setHeureOuverture(serviceModifie.getHeureOuverture());
        serviceExistant.setHeureFermeture(serviceModifie.getHeureFermeture());
        return serviceSportRepository.save(serviceExistant);
    }

    // Supprimer un service sportif
    public void supprimerService(Long id) {
        serviceSportRepository.deleteById(id);
    }
}
