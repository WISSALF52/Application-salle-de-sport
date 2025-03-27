package Service;

import Entité.Cours;
import Entité.Seancecours;
import Repository.CoursRepository;
import Repository.SeancecoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceCours {

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private SeancecoursRepository seancecoursRepository;
    @Autowired
    private final CoursRepository CoursRepository;
    private Object coursRepository;

    public void inscrireAuCours(Long membreId, Long seanceId) {
        // Recherche du membre
        Membre membre = membreRepository.findById(membreId)
                .orElseThrow(() -> new RuntimeException("Membre non trouvé"));

        // Recherche de la séance
        Seancecours seance = seancecoursRepository.findById(seanceId)
                .orElseThrow(() -> new RuntimeException("Séance non trouvée"));

        // Vérification de la capacité
        List<Membre> participants = seance.getParticipants();
        if (participants.size() < seance.getCours().getCapaciteMax()) {
            participants.add(membre);
            seancecoursRepository.save(seance);
        } else {
            throw new RuntimeException("Séance complète");
        }
    }
    public ServiceCours(CoursRepository coursRepository) {
        this.CoursRepository = coursRepository;
    }
    public List<Cours> obtenirTousLesCours() {
        return CoursRepository.findAll();
        }
    public List<Cours> obtenirTousLesCours(String categorie) {
        return CoursRepository.findByCategorie(categorie);
    }
    };





