

package org.example.sport.controllers;

import org.springframework.ui.Model;
import org.example.sport.entite.ServiceSport;
import org.example.sport.repositories.ServiceSportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ServiceController {

    @Autowired
    private ServiceSportRepository serviceRepository; // Assure-toi que tu as un repository pour récupérer les services

    @GetMapping("/serviceslist")
    public String afficherServices(Model model) {
        List<ServiceSport> services = serviceRepository.findAll(); // Récupérer tous les services
        model.addAttribute("services", services); // Ajouter les services au modèle Thymeleaf
        return "services"; // Nom du template Thymeleaf (services.html)
    }

    @GetMapping("/serviceslistes/{id}")
    public String afficherCreneaux(@PathVariable Long id, Model model) {
        ServiceSport service = serviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Service invalide"));
        model.addAttribute("service", service); // Ajouter le service au modèle
        return "creneaux"; // Nom de la page des créneaux
    }
}
