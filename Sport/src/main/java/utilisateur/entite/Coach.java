package utilisateur.entite;

import Entité.Cours;
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
@DiscriminatorValue("COACH")
public class Coach extends Utilisateur {

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<Cours> cours;
}
