package org.example.sport.entite;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idutilisateur;

    @Column(nullable = false, length = 50)
    private String nom;

    @Column(nullable = false, length = 50)
    private String prenom;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // Correction 1 : Renommer en "password" pour matcher le template
    @Column(name = "mot_de_passe", nullable = false)
    private String password;

    // Correction 2 : Ajouter le champ "username"
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "utilisateur_roles", joinColumns = @JoinColumn(name = "utilisateur_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> roles;

    public enum Role {
        CLIENT, COACH, ADMIN
    }
}