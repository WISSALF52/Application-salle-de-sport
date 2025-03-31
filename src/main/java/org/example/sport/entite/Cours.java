package org.example.sport.entite;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcours;

    private String nom;
    private String description;
    private int capaciteMax;

    @ManyToOne
    @JoinColumn(name = "idcoash")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "idservicesport")
    private ServiceSport serviceSport;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeanceCours> seances;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreneauHoraire> creneauxHoraires;
}
