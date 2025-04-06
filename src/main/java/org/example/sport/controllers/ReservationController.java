package org.example.sport.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.sport.entite.Reservation;
import org.example.sport.services.ServiceReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ServiceReservation reservationService;

    @Autowired
    public ReservationController(ServiceReservation reservationService) {
        this.reservationService = reservationService;
    }

    // Affiche le formulaire de réservation
    @GetMapping
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation"; // correspond à reservation.html
    }

    // Traite la soumission du formulaire
    @PostMapping
    public String processReservation(@ModelAttribute("reservation") Reservation reservation,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "reservation"; // reste sur la page si erreurs
        }

        Reservation savedReservation = reservationService.saveReservation(reservation);

        // Ajoute les attributs pour la redirection
        redirectAttributes.addFlashAttribute("reservation", savedReservation);
        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/reservation/confirmation";
    }

    // Affiche la page de confirmation
    @GetMapping("/confirmation")
    public String showConfirmation(Model model) {
        if (!model.containsAttribute("reservation")) {
            return "redirect:/reservation"; // redirige si accès direct
        }
        return "confirmation"; // correspond à confirmation.html
    }
}