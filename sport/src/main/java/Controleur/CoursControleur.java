package Controleur;

import Entité.Cours;
import Service.ServiceCours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cours")
public class CoursControleur {
    @Autowired
    private ServiceCours coursService;

    @GetMapping
    public List<Cours> obtenirTousLesCours() {
        return coursService.obtenirTousLesCours();
    }
    @PostMapping("/inscription")
    public ResponseEntity<String> inscrireAuCours(
            @RequestParam Long membreId,
            @RequestParam Long seanceId
    ) {
        try {
            coursService.inscrireAuCours(membreId,seanceId);
            return ResponseEntity.ok("Inscription réussie au cours");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
