package com.example.demo.controllers;
import com.reservation.sport.entite.*;
import com.reservation.sport.service.*;
import com.example.demo.entite.CodePromo;
import com.example.demo.service.CodePromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/codes-promo")
public class CodePromoController {

    @Autowired
    private CodePromoService codePromoService;

    /**
     * Crée un nouveau code promo
     */
    @PostMapping
    public ResponseEntity<CodePromo> creerCodePromo(@RequestBody Map<String, Object> data) {
        try {
            String code = data.get("code").toString();
            String description = data.get("description").toString();
            BigDecimal valeur = new BigDecimal(data.get("valeur").toString());
            boolean estPourcentage = Boolean.parseBoolean(data.get("estPourcentage").toString());
            LocalDateTime dateDebut = LocalDateTime.parse(data.get("dateDebut").toString());
            LocalDateTime dateExpiration = LocalDateTime.parse(data.get("dateExpiration").toString());
            Integer utilisationsMax = Integer.valueOf(data.get("utilisationsMax").toString());

            CodePromo codePromo = codePromoService.creerCodePromo(
                    code, description, valeur, estPourcentage, dateDebut, dateExpiration, utilisationsMax);

            return ResponseEntity.status(HttpStatus.CREATED).body(codePromo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Désactive un code promo
     */
    @PutMapping("/{id}/desactiver")
    public ResponseEntity<CodePromo> desactiverCodePromo(@PathVariable Long id) {
        try {
            CodePromo codePromo = codePromoService.desactiverCodePromo(id);
            return ResponseEntity.ok(codePromo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}