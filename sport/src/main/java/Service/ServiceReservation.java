package Service;

import Entité.Reservation;
import Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service

public class ServiceReservation {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    public Reservation creerReservation(Reservation reservation) {
        if (verifierDisponibilite(reservation.getDateRéservation())){
            return reservationRepository.save(reservation);
        }
        throw new RuntimeException("Créneau non disponible");
    }

    private boolean verifierDisponibilite(LocalDateTime dateHeure) {
        return reservationRepository.findAll().stream()
                .noneMatch(r -> r.getDateRéservation().equals(dateHeure));
    }

    public void annulerReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> consulterReservationsClient(Long clientId) {
        return reservationRepository.findAll().stream()
                .filter(r -> r.getClient().getId().equals(clientId))
                .collect(Collectors.toList());
    }
}



