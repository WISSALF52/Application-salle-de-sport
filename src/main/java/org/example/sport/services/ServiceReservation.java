package org.example.sport.services;

import org.example.sport.entite.Reservation;
import org.example.sport.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceReservation {

    private final ReservationRepository reservationRepository;

    // Injection du repository par constructeur
    public ServiceReservation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    // Créer une nouvelle réservation
    public Reservation creerReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Obtenir toutes les réservations
    public List<Reservation> obtenirToutesLesReservations() {
        return reservationRepository.findAll();
    }

    // Obtenir une réservation par ID
    public Optional<Reservation> obtenirReservationParId(Long id) {
        return reservationRepository.findById(id);
    }

    // Annuler une réservation
    public void annulerReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    // Confirmer une réservation
    public Reservation confirmerReservation(Long id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();

            return reservationRepository.save(reservation);
        }
        throw new RuntimeException("Réservation non trouvée avec l'id : " + id);
    }


    public Reservation getReservationById(Long id) {
    return null;}
    public Reservation saveReservation(Reservation reservation) {

        reservationRepository.save(reservation);
        return reservation;
    }
}
