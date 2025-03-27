package org.example.sport.entite;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class piscine extends ServiceSport {
    private double profondeur;
    private Double prix;
}

