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
    private Double prix;// 1 = Débutant, 2 = Intermédiaire, 3 = Avancé
}
