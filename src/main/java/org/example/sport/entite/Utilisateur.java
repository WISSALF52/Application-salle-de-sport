package org.example.sport.entite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.JOINED) // Permet d'avoir des tables séparées pour Client et Coach
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur { // Rend la classe abstraite pour éviter son instanciation directe

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idutilisateur;

    @Column(nullable = false, unique = true, length = 50)
    private String nomUtilisateur;  // ➜ Utilisé pour la connexion

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

    // Constructeur sans ID pour simplifier la création d'objets
    public Utilisateur(String nomUtilisateur, String nom, String prenom, String email, String motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }
}
