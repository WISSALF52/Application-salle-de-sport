package org.example.sport.entite;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.example.sport.entite.Cours;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreneauHoraire {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcreneauhoraire;

    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceSport serviceSport;// Un créneau est associé à un service sportif

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours; // Un créneau est associé à un seul cours

    @OneToMany(mappedBy = "creneauHoraire", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations; // Un créneau peut être réservé par plusieurs clients

}

