package org.example.sport.entite;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "code_promo")
public class CodePromo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal valeur;

    @Column(nullable = false)
    private boolean estPourcentage;

    @Column(nullable = false)
    private LocalDateTime dateDebut;

    @Column(nullable = false)
    private LocalDateTime dateExpiration;

    @Column(nullable = false)
    private boolean actif;

    @Column(nullable = false)
    private Integer utilisationsMax;

    @Column(nullable = false)
    private Integer utilisationsActuelles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValeur() {
        return valeur;
    }

    public void setValeur(BigDecimal valeur) {
        this.valeur = valeur;
    }

    public boolean isEstPourcentage() {
        return estPourcentage;
    }

    public void setEstPourcentage(boolean estPourcentage) {
        this.estPourcentage = estPourcentage;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDateTime dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Integer getUtilisationsMax() {
        return utilisationsMax;
    }

    public void setUtilisationsMax(Integer utilisationsMax) {
        this.utilisationsMax = utilisationsMax;
    }

    public Integer getUtilisationsActuelles() {
        return utilisationsActuelles;
    }

    public void setUtilisationsActuelles(Integer utilisationsActuelles) {
        this.utilisationsActuelles = utilisationsActuelles;
    }

}
