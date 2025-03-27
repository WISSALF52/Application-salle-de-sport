package Repository;

import Entité.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Méthodes personnalisées si nécessaire
}

