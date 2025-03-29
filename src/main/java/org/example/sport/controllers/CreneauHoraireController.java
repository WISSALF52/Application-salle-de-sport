package org.example.sport.controllers;

import org.example.sport.entite.CreneauHoraire;
import org.example.sport.services.CreneauHoraireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creneaux")
public class CreneauHoraireController {

    private final CreneauHoraireService creneauHoraireService;

    public CreneauHoraireController(CreneauHoraireService creneauHoraireService) {
        this.creneauHoraireService = creneauHoraireService;
    }

    // Ajouter un créneau
    @PostMapping
    public ResponseEntity<CreneauHoraire> ajouterCreneau(@RequestBody CreneauHoraire creneau) {
        return ResponseEntity.ok(creneauHoraireService.ajouterCreneau(creneau));
    }

    // Obtenir tous les créneaux
    @GetMapping
    public ResponseEntity<List<CreneauHoraire>> obtenirTousLesCreneaux() {
        return ResponseEntity.ok(creneauHoraireService.obtenirTousLesCreneaux());
    }

    // Obtenir un créneau par ID
    @GetMapping("/{id}")
    public ResponseEntity<CreneauHoraire> obtenirCreneauParId(@PathVariable Long id) {
        return ResponseEntity.ok(creneauHoraireService.obtenirCreneauParId(id));
    }

    // Mettre à jour un créneau
    @PutMapping("/{id}")
    public ResponseEntity<CreneauHoraire> mettreAJourCreneau(@PathVariable Long id, @RequestBody CreneauHoraire creneau) {
        return ResponseEntity.ok(creneauHoraireService.mettreAJourCreneau(id, creneau));
    }

    // Supprimer un créneau
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCreneau(@PathVariable Long id) {
        creneauHoraireService.supprimerCreneau(id);
        return ResponseEntity.noContent().build();
    }
}
