package org.example.sport.entite;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class musculation extends ServiceSport {
    private int nombreAppareils;
    private boolean coachDisponible;
    private String typeMateriel;
    private Double prix;
}


