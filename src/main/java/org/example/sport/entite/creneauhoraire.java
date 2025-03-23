package org.example.sport.entite;
import jakarta.persistence.*;
        import lombok.*;

        import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class creneauhoraire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceSport ServiceSport; // Un créneau est associé à un service sportif
}

