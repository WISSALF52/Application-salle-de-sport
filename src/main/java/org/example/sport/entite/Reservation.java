package org.example.sport.entite;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // <-- Clé primaire

    private String nomComplet;
    private String email;
    private String telephone;
    private LocalDate dateReservation;
    private String methodePaiement;
    private String message;
    private boolean conditionsAcceptees;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "cours_id")
    private Cours cours;

    @ManyToOne
    @JoinColumn(name = "creneau_id")
    private CreneauHoraire creneauHoraire;

    // Constructeurs
    public Reservation() {
    }

    public Reservation(String nomComplet, String email, String telephone, LocalDate dateReservation,
                       String methodePaiement, String message, boolean conditionsAcceptees) {
        this.nomComplet = nomComplet;
        this.email = email;
        this.telephone = telephone;
        this.dateReservation = dateReservation;
        this.methodePaiement = methodePaiement;
        this.message = message;
        this.conditionsAcceptees = conditionsAcceptees;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getMethodePaiement() {
        return methodePaiement;
    }

    public void setMethodePaiement(String methodePaiement) {
        this.methodePaiement = methodePaiement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isConditionsAcceptees() {
        return conditionsAcceptees;
    }

    public void setConditionsAcceptees(boolean conditionsAcceptees) {
        this.conditionsAcceptees = conditionsAcceptees;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public CreneauHoraire getCreneauHoraire() {
        return creneauHoraire;
    }

    public void setCreneauHoraire(CreneauHoraire creneauHoraire) {
        this.creneauHoraire = creneauHoraire;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", nomComplet='" + nomComplet + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", dateReservation=" + dateReservation +
                ", methodePaiement='" + methodePaiement + '\'' +
                ", message='" + message + '\'' +
                ", conditionsAcceptees=" + conditionsAcceptees +
                ", client=" + client +
                ", cours=" + cours +
                ", creneauHoraire=" + creneauHoraire +
                '}';
    }
}
