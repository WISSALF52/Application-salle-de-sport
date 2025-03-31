package org.example.sport.entite;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idreservation;

    private String nomUtilisateur; // Nom de l'utilisateur qui réserve
    private LocalDateTime dateReservation;

    @ManyToOne
    @JoinColumn(name = "idcours")
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "idservicesport")
    private ServiceSport service;

    @ManyToOne
    @JoinColumn(name = "idcreneauhoraire")
    private CreneauHoraire creneauHoraire;

    @ManyToOne
    @JoinColumn(name = "idclient")
    private Client client;

    // Relation OneToOne avec Paiement
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Paiement paiement;

    public Client getUtilisateur() {
        return null;
    }

    public void setStatut(boolean b) {
    }

    public LocalDateTime getDateRéservation() {
        return null;
    }
}
