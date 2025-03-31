
package org.example.sport.controllers;
import org.example.sport.services.serviceSeancecours;
import org.example.sport.entite.SeanceCours;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seances")
public class SeanceCoursControleur {
    private final serviceSeancecours seancecours;

    @Autowired
    public SeanceCoursControleur(serviceSeancecours seancecours) {
        this.seancecours = seancecours;
    }

    // Méthode pour planifier une nouvelle séance
    @PostMapping("/planifier")
    public ResponseEntity<SeanceCours> planifierSeance(@RequestBody SeanceCours seance) {
        try {
            SeanceCours seancePlanifiee = seancecours.planifierSeance(seance);
            return ResponseEntity.status(HttpStatus.CREATED).body(seancePlanifiee);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
