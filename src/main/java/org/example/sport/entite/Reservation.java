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
    private Long id;

    private String nomUtilisateur; // Nom de l'utilisateur qui réserve
    private LocalDateTime dateReservation;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceSport service;

    @ManyToOne
    @JoinColumn(name = "creneau_horaire_id")
    private CreneauHoraire creneauHoraire;

    @ManyToOne
    @JoinColumn(name = "client_id")
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
