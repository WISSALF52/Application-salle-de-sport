package Entité;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    private String nomUtilisateur; // Nom de l'utilisateur qui réserve
    private LocalDateTime dateRéservation;
    private Service service;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public LocalDateTime getDateRéservation() {
        return dateRéservation;
    }

    public void setDateRéservation(LocalDateTime dateRéservation) {
        this.dateRéservation = dateRéservation;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}



