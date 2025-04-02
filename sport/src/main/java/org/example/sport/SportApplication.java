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
                Utilisateur admin = new Utilisateur();
                admin.setNom("Admin");
                admin.setPrenom("System");
                admin.setEmail("admin@sport.com");
                admin.setMotDePasse(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of((Utilisateur.Role.ADMIN),(Utilisateur.Role.COACH),(Utilisateur.Role.CLIENT)));

                Utilisateur client = new Utilisateur();
                client.setNom("Client");
                client.setPrenom("Test");
                client.setEmail("client@sport.com");
                client.setMotDePasse(passwordEncoder.encode("client123"));
                client.setRoles(Set.of(Utilisateur.Role.CLIENT));

                Utilisateur coach = new Utilisateur();
                coach.setNom("Coach");
                coach.setPrenom("Sportif");
                coach.setEmail("coach@sport.com");
                coach.setMotDePasse(passwordEncoder.encode("coach123"));
                coach.setRoles(Set.of(Utilisateur.Role.COACH));

                utilisateurRepository.save(admin);
                utilisateurRepository.save(client);
                utilisateurRepository.save(coach);

                System.out.println("✅ Utilisateurs initiaux insérés : admin, client, coach.");
            }
        };
    }
}