package org.example.sport.entite;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "paiement")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpaiement;

    // Relation avec Client : ManyToOne (Un client peut avoir plusieurs paiements)
    @ManyToOne
    @JoinColumn(name = "idclient") // Assurez-vous que ce nom correspond à votre base de données
    private Client client;

    // Relation avec Reservation : OneToOne (Chaque paiement correspond à une réservation unique)
    @OneToOne
    @JoinColumn(name = "idreservation")
    private Reservation reservation;

    @Column(nullable = false)
    private BigDecimal montant;

    @Column(nullable = false)
    private LocalDateTime datePaiement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutPaiement statut;

    @Column(length = 100)
    private String referencePaiement;

    // Relation avec CodePromo : ManyToOne (Un paiement peut avoir un code promo)
    @ManyToOne
    @JoinColumn(name = "idcodepromo")
    private CodePromo codePromo;

    // Getters et Setters
    public Long getIdpaiement() {
        return idpaiement;
    }

    public void setIdpaiement(Long idpaiement) {
        this.idpaiement = idpaiement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }

    public StatutPaiement getStatut() {
        return statut;
    }

    public void setStatut(StatutPaiement statut) {
        this.statut = statut;
    }

    public String getReferencePaiement() {
        return referencePaiement;
    }

    public void setReferencePaiement(String referencePaiement) {
        this.referencePaiement = referencePaiement;
    }

    public CodePromo getCodePromo() {
        return codePromo;
    }

    public void setCodePromo(CodePromo codePromo) {
        this.codePromo = codePromo;
    }
}
