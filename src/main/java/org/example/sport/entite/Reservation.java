
package org.example.sport.entite;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String selectedCourse;
    private LocalDateTime dateTime;
    private double price;

    @ManyToOne
    @JoinColumn(name = "client_id") // Référence à l'entité Client
    private Client client;

    @ManyToOne
    @JoinColumn(name = "cours_id") // Référence à l'entité Cours
    private Cours cours;

    // Constructeurs, getters et setters
    public LocalDateTime getDateRéservation() {
        return this.dateTime; // Retourne la date de réservation
    }

    public Client getClient() {
        return this.client; // Retourne l'objet client associé à cette réservation
    }

    public LocalDateTime setDateTime(LocalDateTime now) {
        return now;
    }
    @ManyToOne
    @JoinColumn(name = "creneau_horaire_id")
    private CreneauHoraire creneauHoraire;

    // Autres getters et setters
}
