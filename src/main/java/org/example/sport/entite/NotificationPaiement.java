package org.example.sport.entite;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "notification_paiement")
public class NotificationPaiement {
    public Long getId() {
        return idnotification;
    }

    public void setId(Long id) {
        this.idnotification = id;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public Remboursement getRemboursement() {
        return remboursement;
    }

    public void setRemboursement(Remboursement remboursement) {
        this.remboursement = remboursement;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public boolean isEstLu() {
        return estLu;
    }

    public void setEstLu(boolean estLu) {
        this.estLu = estLu;
    }

    public TypeNotification getType() {
        return type;
    }

    public void setType(TypeNotification type) {
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idnotification;

    //@ManyToOne
    //@JoinColumn(name = "utilisateur_id", nullable = false)
    //private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "paiement_id")
    private Paiement paiement;

    @ManyToOne
    @JoinColumn(name = "remboursement_id")
    private Remboursement remboursement;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;  // ou Client si tu as changé le nom de l'entité


    @Column(nullable = false, length = 255)
    private String message;

    @Column(nullable = false)
    private LocalDateTime dateEnvoi;

    @Column(nullable = false)
    private boolean estLu;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeNotification type;


    public void setUtilisateur(Utilisateur utilisateur) {
        
    }
}
