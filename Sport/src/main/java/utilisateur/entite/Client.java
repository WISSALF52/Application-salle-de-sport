package utilisateur.entite;

import Entité.Reservation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.example.sport.entite.Utilisateur;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("CLIENT")
public class Client extends Utilisateur {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Paiement> paiements;
}
