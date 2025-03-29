package org.example.sport.controllers;

import org.example.sport.entite.ServiceSport;
import org.example.sport.services.ServiceSportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceSportController {

    private final ServiceSportService serviceSportService;

    public ServiceSportController(ServiceSportService serviceSportService) {
        this.serviceSportService = serviceSportService;
    }

    // 🔹 Ajouter un nouveau service sportif
    @PostMapping
    public ResponseEntity<ServiceSport> ajouterService(@RequestBody ServiceSport service) {
        ServiceSport nouveauService = serviceSportService.ajouterService(service);
        return ResponseEntity.ok(nouveauService);
    }

    // 🔹 Obtenir tous les services sportifs
    @GetMapping
    public ResponseEntity<List<ServiceSport>> obtenirTousLesServices() {
        List<ServiceSport> services = serviceSportService.obtenirTousLesServices();
        return ResponseEntity.ok(services);
    }

    // 🔹 Obtenir un service sportif par ID
    @GetMapping("/{id}")
    public ResponseEntity<ServiceSport> obtenirServiceParId(@PathVariable Long id) {
        ServiceSport service = serviceSportService.obtenirServiceParId(id);
        return ResponseEntity.ok(service);
    }

    // 🔹 Mettre à jour un service sportif
    @PutMapping("/{id}")
    public ResponseEntity<ServiceSport> mettreAJourService(@PathVariable Long id, @RequestBody ServiceSport serviceModifie) {
        ServiceSport serviceMiseAJour = serviceSportService.mettreAJourService(id, serviceModifie);
        return ResponseEntity.ok(serviceMiseAJour);
    }

    // 🔹 Supprimer un service sportif
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerService(@PathVariable Long id) {
        serviceSportService.supprimerService(id);
        return ResponseEntity.noContent().build();
    }
}
