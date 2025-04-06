package org.example.sport.controllers;

import org.example.sport.entite.Utilisateur;
import org.example.sport.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Afficher le formulaire d'inscription
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "register";
    }

    // Traiter la soumission du formulaire
    @PostMapping("/register")
    public String processRegistration(
            @ModelAttribute("utilisateur") Utilisateur utilisateur,
            Model model
    ) {
        // Vérification de l'existence de l'email
        if (utilisateurService.trouverParEmail(utilisateur.getEmail()).isPresent()) {
            model.addAttribute("error", "Cet email est déjà utilisé");
            return "register";
        }

        try {
            utilisateurService.inscrireUtilisateur(utilisateur);
            model.addAttribute("success", "Inscription réussie !");
            return "redirect:/login"; // Redirection pour éviter la resoumission
        } catch (Exception e) {
            model.addAttribute("error", "Erreur technique : " + e.getMessage());
            return "register";
        }
    }
}