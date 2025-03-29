package org.example.sport.entite;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class spa extends ServiceSport {
    private boolean hammamDisponible;
    private boolean saunaDisponible;
    private boolean jacuzziDisponible;
    private int nombreCabinesMassage;
    private boolean estheticienneDisponible;
    private double prix;
}

