package org.example.sport.entite;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Crée une table par sous-classe
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ServiceSport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private double prix;
    private int capaciteMax; // Nombre maximal de personnes
    private LocalTime heureOuverture;
    private LocalTime heureFermeture;
    @OneToMany(mappedBy = "serviceSport")
    private Set<CreneauHoraire> creneauhoraire;

    @OneToMany(mappedBy = "serviceSport", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cours> cours;
}




