package utilisateur.services;

import org.example.sport.entite.Utilisateur;
import org.example.sport.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        return utilisateurRepository.save(utilisateur);
    }

    public Optional<Utilisateur> trouverParEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur mettreAJourUtilisateur(Long id, Utilisateur utilisateurDetails) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setNom(utilisateurDetails.getNom());
            utilisateur.setPrenom(utilisateurDetails.getPrenom());
            utilisateur.setEmail(utilisateurDetails.getEmail());
            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDetails.getMotDePasse()));
            return utilisateurRepository.save(utilisateur);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !"));
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public Iterable<Utilisateur> obtenirTousLesUtilisateurs() {
        return utilisateurRepository.findAll();
    }


    public Utilisateur verifierOuInscrireUtilisateur(String email, String nom, String prenom, String motDePasse) {
        Optional<Utilisateur> utilisateurExistant = utilisateurRepository.findByEmail(email);

        if (utilisateurExistant.isPresent()) {
            return utilisateurExistant.get(); // Retourne l'utilisateur existant
        } else {
            // L'utilisateur n'existe pas → on crée un compte
            Utilisateur nouvelUtilisateur = new Utilisateur();
            nouvelUtilisateur.setNom(nom);
            nouvelUtilisateur.setPrenom(prenom);
            nouvelUtilisateur.setEmail(email);
            nouvelUtilisateur.setMotDePasse(passwordEncoder.encode("default123")); // Mot de passe temporaire

            return utilisateurRepository.save(nouvelUtilisateur);
        }
    }


}
