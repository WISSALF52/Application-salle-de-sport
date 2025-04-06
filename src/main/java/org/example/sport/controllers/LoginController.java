package org.example.sport.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // Afficher la page de connexion
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
        }
        if (logout != null) {
            model.addAttribute("message", "Vous avez été déconnecté avec succès");
        }
        return "login";  // Renvoie le template login.html
    }

    // Gérer la connexion, ce qui est fait automatiquement par Spring Security
    @PostMapping("/login")
    public String authenticateUser(@RequestParam String email, @RequestParam String password) {
        // Authentifier l'utilisateur via l'AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Si l'authentification réussie, l'utilisateur sera redirigé
        if (authentication.isAuthenticated()) {
            return "redirect:/acceuil";  // Redirige vers la page d'accueil après une connexion réussie
        }

        // En cas d'échec de l'authentification, l'utilisateur reste sur la page de connexion
        return "redirect:/login?error=true";
}
}
