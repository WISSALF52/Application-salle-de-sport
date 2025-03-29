package org.example.sport.entite;



import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class yoga extends ServiceSport {
    private int niveauDifficulte;
    private double prix;// 1 = Débutant, 2 = Intermédiaire, 3 = Avancé
}
