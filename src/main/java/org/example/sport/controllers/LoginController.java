package org.example.sport.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

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

    // La méthode POST /login est gérée automatiquement par Spring Security
    // Pas besoin de la redéfinir manuellement

    // Redirection après connexion réussie
    @GetMapping("/redirectAfterLogin")
    public String redirectAfterLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        }
        return "redirect:/acceuil";
    }
}