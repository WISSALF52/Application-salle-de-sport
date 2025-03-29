package org.example.sport.entite;

import org.example.sport.entite.Cours;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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