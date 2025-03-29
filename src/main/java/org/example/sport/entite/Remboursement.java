package org.example.sport.entite;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "remboursement")
public class Remboursement {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateRemboursement() {
        return dateRemboursement;
    }

    public void setDateRemboursement(LocalDateTime dateRemboursement) {
        this.dateRemboursement = dateRemboursement;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public StatutRemboursement getStatut() {
        return statut;
    }

    public void setStatut(StatutRemboursement statut) {
        this.statut = statut;
    }

    public String getReferenceRemboursement() {
        return referenceRemboursement;
    }

    public void setReferenceRemboursement(String referenceRemboursement) {
        this.referenceRemboursement = referenceRemboursement;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paiement_id", nullable = false)
    private Paiement paiement;

    @Column(nullable = false)
    private BigDecimal montant;

    @Column(nullable = false)
    private LocalDateTime dateRemboursement;

    @Column(nullable = false, length = 255)
    private String motif;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutRemboursement statut;

    @Column(length = 100)
    private String referenceRemboursement;


}
