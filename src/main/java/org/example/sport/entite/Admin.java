package org.example.sport.entite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("ADMIN")
@Getter
@Setter
public class Admin extends Utilisateur {



    public Admin() {
        this.getRoles().add(Role.ADMIN);
    }
}