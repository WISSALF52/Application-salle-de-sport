package Controleur;

import Entité.Seancecours;
import Service.ServiceSeancecours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seances")
public class SeancecoursControleur {
    private final ServiceSeancecours seancecours;

    @Autowired
    public SeancecoursControleur(ServiceSeancecours seancecours) {
        this.seancecours = seancecours;
    }

    // Méthode pour planifier une nouvelle séance
    @PostMapping("/planifier")
    public ResponseEntity<Seancecours> planifierSeance(@RequestBody Seancecours seance) {
        try {
            Seancecours seancePlanifiee = seancecours.planifierSeance(seance);
            return ResponseEntity.status(HttpStatus.CREATED).body(seancePlanifiee);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
