package org.example.sport.entite;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity
public class spo {

    @Id
    private Long id;          // Clé primaire
    private String nom;       // Autres attributs

    // Constructeurs, getters et setters

    public spo() {
    }

    public spo(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

