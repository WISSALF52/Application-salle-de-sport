package org.example.sport.controllers;

import org.example.sport.entite.Contact;
import org.example.sport.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:8083") // Autoriser les requêtes provenant du frontend
public class ContactController {

    @Autowired
    private ContactService contactService;

    // Affichage du formulaire de contact
    @GetMapping
    public String showContactForm() {
        return "contact"; // Retourne le template contact.html
    }

    // Soumission du formulaire de contact
    @PostMapping
    public String submitContactForm(@ModelAttribute Contact contactMessage, RedirectAttributes redirectAttributes) {
        // Sauvegarder le message dans la base de données
        contactService.saveContactMessage(contactMessage);

        // Ajouter un message de succès dans les flash attributes pour redirection
        redirectAttributes.addFlashAttribute("message", "Votre message a été envoyé avec succès!");

        // Rediriger vers la page de confirmation (message)
        return "redirect:/contact/message"; // Rediriger vers la page message.html
    }

    // Affichage de la page de confirmation après la soumission du formulaire
    @GetMapping("/message")
    public String showMessage(Model model) {
        // Récupérer le message de succès à partir des flash attributes et l'ajouter au modèle
        String successMessage = (String) model.asMap().get("message");
        if (successMessage == null) {
            successMessage = "Votre message a été envoyé avec succès!";
        }
        model.addAttribute("message", successMessage); // Ajouter le message de succès au modèle

        return "message"; // Retourne la page message.html qui affiche le message de succès
    }
}
