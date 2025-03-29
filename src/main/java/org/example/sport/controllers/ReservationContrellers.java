package org.example.sport.controllers;
import org.example.sport.entite.Reservation;
import org.example.sport.services.ServiceReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/réservations")
public class ReservationContrellers {
    @Autowired
    private ServiceReservation reservationService;

    @PostMapping("/reservations")
    public Reservation creerReservation(@RequestBody Reservation reservation) {
        return reservationService.creerReservation(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public void annulerReservation(@PathVariable Long id) {
        reservationService.annulerReservation(id);
    }

    @GetMapping("/clients/{clientId}/reservations")
    public List<Reservation> consulterReservations(@PathVariable Long clientId) {
        return reservationService.consulterReservationsClient(clientId);
}

}

