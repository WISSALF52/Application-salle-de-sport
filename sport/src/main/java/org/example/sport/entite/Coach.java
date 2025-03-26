package org.example.sport.entite;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("COACH")
public class Coach extends Utilisateur {

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<Cours> cours;
}
