package org.example.sport.entite;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "seance_cours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SeanceCours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idseancecours;

    private LocalDateTime dateHeure;
    private int duree;

    @ManyToOne
    @JoinColumn(name = "idcours")
    private Cours cours;

    @ManyToMany
    @JoinTable(
            name = "idseancecours",
            joinColumns = @JoinColumn(name = "idseance"),
            inverseJoinColumns = @JoinColumn(name = "idclient")
    )
    private List<Client> participants;
}
