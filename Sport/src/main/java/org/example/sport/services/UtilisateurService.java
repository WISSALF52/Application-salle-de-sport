package org.example.sport.services;

import org.example.sport.entite.Utilisateur;
import org.example.sport.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 🔹 Inscription d'un nouvel utilisateur avec hash du mot de passe
    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {
        if (utilisateurRepository.findByNomUtilisateur(utilisateur.getNomUtilisateur()).isPresent()) {
            throw new RuntimeException("Nom d'utilisateur déjà utilisé !");
        }
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    // 🔹 Trouver un utilisateur par nom d'utilisateur
    public Optional<Utilisateur> trouverParNomUtilisateur(String nomUtilisateur) {
        return utilisateurRepository.findByNomUtilisateur(nomUtilisateur);
    }

    // 🔹 Mettre à jour un utilisateur existant
    public Utilisateur mettreAJourUtilisateur(Long id, Utilisateur utilisateur) {
        Optional<Utilisateur> utilisateurExistant = utilisateurRepository.findById(id);

        if (utilisateurExistant.isPresent()) {
            Utilisateur u = utilisateurExistant.get();
            u.setNomUtilisateur(utilisateur.getNomUtilisateur());  // ➜ Mise à jour du nom d'utilisateur
            u.setNom(utilisateur.getNom());
            u.setPrenom(utilisateur.getPrenom());
            u.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
            u.setRoles(utilisateur.getRoles());
            return utilisateurRepository.save(u);
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'ID : " + id);
        }
    }

    // 🔹 Supprimer un utilisateur par ID
    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    // 🔹 Récupérer tous les utilisateurs
    public Iterable<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }
}

