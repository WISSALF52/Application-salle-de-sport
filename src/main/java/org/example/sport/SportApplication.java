package org.example.sport;

import org.example.sport.entite.Utilisateur;
import org.example.sport.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class SportApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportApplication.class, args);
    }

@Bean
public CommandLineRunner initData(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
    return args -> {
        if (utilisateurRepository.count() == 0) {
            // Création de l'admin
            Utilisateur admin = new Utilisateur();
            admin.setNom("Admin");
            admin.setPrenom("System");
            admin.setUsername("admin"); // Ajout du username
            admin.setEmail("admin@sport.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(Set.of(Utilisateur.Role.ADMIN, Utilisateur.Role.CLIENT));

            // Création du client
            Utilisateur client = new Utilisateur();
            client.setNom("Client");
            client.setPrenom("Test");
            client.setUsername("client"); // Ajout du username
            client.setEmail("client@sport.com");
            client.setPassword(passwordEncoder.encode("client123"));
            client.setRoles(Set.of(Utilisateur.Role.CLIENT));

            // Création du client2
            Utilisateur client2 = new Utilisateur();
            client2.setNom("Client2"); // Correction ici (client au lieu de client2)
            client2.setPrenom("Test2");
            client2.setUsername("client2"); // Ajout du username
            client2.setEmail("client2@sport.com");
            client2.setPassword(passwordEncoder.encode("client1234"));
            client2.setRoles(Set.of(Utilisateur.Role.CLIENT));

            // Sauvegarde des utilisateurs
            utilisateurRepository.save(admin);
            utilisateurRepository.save(client);
            utilisateurRepository.save(client2);

            System.out.println("✅ Utilisateurs initiaux insérés : admin, client, client2.");
        }
    };
}}
