package org.example.sport.controllers;

import org.example.sport.entite.Reservation;
import org.example.sport.services.ServiceReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final ServiceReservation reservationService;

    @Autowired
    public ReservationController(ServiceReservation reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/form")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation-form";
    }

    @PostMapping
    public String submitReservationForm(
            @Valid @ModelAttribute("reservation") Reservation reservation,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        // Validation des erreurs
        if (bindingResult.hasErrors()) {
            return "reservation";
        }

        // Ajout de la date/heure actuelle
        reservation.setDateTime(LocalDateTime.now());

        // Sauvegarde en base de données
        reservationService.save(reservation);

        // Préparation des attributs pour la redirection
        redirectAttributes.addFlashAttribute("successMessage", "Réservation confirmée !");
        redirectAttributes.addFlashAttribute("reservation", reservation);

        return "redirect:/reservations/confirmation";
    }

    @GetMapping("/confirmation")
    public String showConfirmationPage() {
        return "confirmation";
    }
}