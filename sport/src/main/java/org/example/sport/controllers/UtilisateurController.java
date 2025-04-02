package org.example.sport.controllers;

import org.example.sport.entite.Utilisateur;
import org.example.sport.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/inscription")
    public ResponseEntity<Utilisateur> inscrireUtilisateur(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.inscrireUtilisateur(utilisateur));
    }

    @GetMapping("/chercher")
    public ResponseEntity<Utilisateur> obtenirUtilisateurParEmail(@RequestParam String email) {
        Optional<Utilisateur> utilisateur = utilisateurService.trouverParEmail(email);
        return utilisateur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/mise-a-jour/{id}")
    public ResponseEntity<Utilisateur> mettreAJourUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurMisAJour = utilisateurService.mettreAJourUtilisateur(id, utilisateur);
        return ResponseEntity.ok(utilisateurMisAJour);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tous")
    public ResponseEntity<Iterable<Utilisateur>> obtenirTousLesUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.obtenirTousLesUtilisateurs());
    }

    @GetMapping("/home")
    public String home() {
        return "Welcome, Sportifs";
    }
}
