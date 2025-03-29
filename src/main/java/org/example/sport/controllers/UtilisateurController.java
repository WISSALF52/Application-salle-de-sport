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

        // 🔹 Inscription d'un utilisateur
        @PostMapping("/inscription")
        public ResponseEntity<Utilisateur> inscrireUtilisateur(@RequestBody Utilisateur utilisateur) {
            return ResponseEntity.ok(utilisateurService.inscrireUtilisateur(utilisateur));
        }

        // 🔹 Connexion (vérification du nom d'utilisateur et du mot de passe)
        @PostMapping("/connexion")
        public ResponseEntity<String> connexion(@RequestParam String nomUtilisateur, @RequestParam String motDePasse) {
            Optional<Utilisateur> utilisateur = utilisateurService.trouverParNomUtilisateur(nomUtilisateur);
            if (utilisateur.isPresent()) {
                Utilisateur u = utilisateur.get();
                if (utilisateurService.verifierMotDePasse(motDePasse, u.getMotDePasse())) {
                    return ResponseEntity.ok("Connexion réussie !");
                }
            }
            return ResponseEntity.status(401).body("Nom d'utilisateur ou mot de passe incorrect !");
        }

        // 🔹 Récupérer un utilisateur par nomUtilisateur
        @GetMapping("/chercher")
        public ResponseEntity<Utilisateur> obtenirUtilisateurParNomUtilisateur(@RequestParam String nomUtilisateur) {
            Optional<Utilisateur> utilisateur = utilisateurService.trouverParNomUtilisateur(nomUtilisateur);
            return utilisateur.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }

        // 🔹 Mise à jour d'un utilisateur
        @PutMapping("/mise-a-jour/{id}")
        public ResponseEntity<Utilisateur> mettreAJourUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
            Utilisateur misAJour = utilisateurService.mettreAJourUtilisateur(id, utilisateur);
            return ResponseEntity.ok(misAJour);
        }

        // 🔹 Suppression d'un utilisateur par ID
        @DeleteMapping("/supprimer/{id}")
        public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
            utilisateurService.supprimerUtilisateur(id);
            return ResponseEntity.noContent().build();
        }

        // 🔹 Récupérer tous les utilisateurs
        @GetMapping("/tous")
        public ResponseEntity<Iterable<Utilisateur>> obtenirTousLesUtilisateurs() {
            Iterable<Utilisateur> utilisateurs = utilisateurService.obtenirTousLesUtilisateurs();
            return ResponseEntity.ok(utilisateurs);
        }
    }

