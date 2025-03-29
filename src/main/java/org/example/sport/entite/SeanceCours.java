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
    private Long id;

    private LocalDateTime dateHeure;
    private int duree;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    @ManyToMany
    @JoinTable(
            name = "seance_client",
            joinColumns = @JoinColumn(name = "seance_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> participants;
}
