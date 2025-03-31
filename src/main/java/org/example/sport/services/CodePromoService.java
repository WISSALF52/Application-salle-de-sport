package org.example.sport.services;
import org.example.sport.entite.CodePromo;
import org.example.sport.repositories.CodePromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CodePromoService {

    @Autowired
    private CodePromoRepository codePromoRepository;

    /**
     * Crée un nouveau code promo
     */
    @Transactional
    public CodePromo creerCodePromo(String code, String description, BigDecimal valeur,
                                    boolean estPourcentage, LocalDateTime dateDebut,
                                    LocalDateTime dateExpiration, Integer utilisationsMax) {
        // Vérifier si le code existe déjà
        if (codePromoRepository.findByCode(code).isPresent()) {
            throw new IllegalArgumentException("Ce code promo existe déjà");
        }

        CodePromo codePromo = new CodePromo();
        codePromo.setCode(code);
        codePromo.setDescription(description);
        codePromo.setValeur(valeur);
        codePromo.setEstPourcentage(estPourcentage);
        codePromo.setDateDebut(dateDebut);
        codePromo.setDateExpiration(dateExpiration);
        codePromo.setActif(true);
        codePromo.setUtilisationsMax(utilisationsMax);
        codePromo.setUtilisationsActuelles(0);

        return codePromoRepository.save(codePromo);
    }

    /**
     * Désactive un code promo
     */
    @Transactional
    public CodePromo desactiverCodePromo(Long codePromoId) {
        CodePromo codePromo = codePromoRepository.findById(codePromoId)
                .orElseThrow(() -> new IllegalArgumentException("Code promo non trouvé"));

        codePromo.setActif(false);
        return codePromoRepository.save(codePromo);
    }

    /**
     * Vérifie la validité d'un code promo
     */
    public boolean verifierValiditeCodePromo(String code) {
        Optional<Object> optCodePromo = codePromoRepository.findByCode(code);

        if (optCodePromo.isEmpty()) {
            return false;
        }

        CodePromo codePromo = (CodePromo) optCodePromo.get();
        LocalDateTime maintenant = LocalDateTime.now();

        return codePromo.isActif() &&
                maintenant.isAfter(codePromo.getDateDebut()) &&
                maintenant.isBefore(codePromo.getDateExpiration()) &&
                codePromo.getUtilisationsActuelles() < codePromo.getUtilisationsMax();
    }

    /**
     * Récupère tous les codes promo actifs
     */
    public List<CodePromo> obtenirCodesPromoActifs() {
        return codePromoRepository.findByActifAndDateExpirationAfter(true, LocalDateTime.now());
    }
}
