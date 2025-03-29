package org.example.sport.controllers;
import org.example.sport.entite.Paiement;
import org.example.sport.entite.Remboursement;
import org.example.sport.services.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    /**
     * Crée un nouveau paiement
     */
    @PostMapping
    public ResponseEntity<Paiement> creerPaiement(@RequestBody Map<String, Object> data) {
        try {
            Long utilisateurId = Long.valueOf(data.get("utilisateurId").toString());
            Long reservationId = Long.valueOf(data.get("reservationId").toString());
            BigDecimal montant = new BigDecimal(data.get("montant").toString());
            String codePromo = data.get("codePromo") != null ? data.get("codePromo").toString() : null;

            Paiement paiement = paiementService.creerPaiement(utilisateurId, reservationId, montant, codePromo);
            return ResponseEntity.status(HttpStatus.CREATED).body(paiement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Confirme un paiement
     */
    @PostMapping("/confirmer")
    public ResponseEntity<Paiement> confirmerPaiement(@RequestBody Map<String, String> data) {
        try {
            String referencePaiement = data.get("referencePaiement");
            Paiement paiement = paiementService.confirmerPaiement(referencePaiement);
            return ResponseEntity.ok(paiement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Crée un remboursement
     */
    @PostMapping("/remboursements")
    public ResponseEntity<Remboursement> creerRemboursement(@RequestBody Map<String, Object> data) {
        try {
            Long paiementId = Long.valueOf(data.get("paiementId").toString());
            String motif = data.get("motif").toString();

            Remboursement remboursement = paiementService.creerRemboursement(paiementId, motif);
            return ResponseEntity.status(HttpStatus.CREATED).body(remboursement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Confirme un remboursement
     */
    @PostMapping("/remboursements/confirmer")
    public ResponseEntity<Remboursement> confirmerRemboursement(@RequestBody Map<String, String> data) {
        try {
            String referenceRemboursement = data.get("referenceRemboursement");
            Remboursement remboursement = paiementService.confirmerRemboursement(referenceRemboursement);
            return ResponseEntity.ok(remboursement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }}
