package org.example.sport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class avisclient {
    // Méthode pour afficher la page d'accueil
    @GetMapping("/avisclient")
    public String home() {
        return "avisclient";  // Le nom du fichier HTML sans l'extension .html
    }
}

