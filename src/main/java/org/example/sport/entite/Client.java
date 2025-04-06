package org.example.sport.entite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("CLIENT")
@Getter
@Setter
public class Client extends Utilisateur {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    // Propriétés spécifiques au client

    public Client() {
        this.getRoles().add(Role.CLIENT);
    }
}