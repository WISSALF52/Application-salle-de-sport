package org.example.sport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
@Controller
public class fitness {

    // Méthode pour afficher la page fitness
    @GetMapping("/fitness")
    public String fitnessPage() {
        return "fitness"; // Correspond à fitness.html dans le dossier templates
    }

    // Méthode pour afficher la page musculation
    @GetMapping("/musculation")
    public String musculationPage() {
        return "musculation"; // Correspond à musculation.html dans le dossier templates
    }
    @GetMapping("/yoga")
    public String yogaPage() {
        return "yoga"; // Correspond à musculation.html dans le dossier templates
    }
    @GetMapping("/dance")
    public String dancePage() {
        return "dance"; // Correspond à musculation.html dans le dossier templates
    }
    @GetMapping("/spa")
    public String spaPage() {
        return "spa"; // Correspond à musculation.html dans le dossier templates
    }
    @GetMapping("/piscine")
    public String piscinePage() {
        return "piscine"; // Correspond à musculation.html dans le dossier templates
    }

    }


