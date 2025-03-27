package org.example.sport.entite;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class fitness extends ServiceSport {
    private boolean coachDisponible;
    private Double prix;
}

