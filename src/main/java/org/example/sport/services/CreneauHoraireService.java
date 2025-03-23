package org.example.sport.services;

import org.example.sport.entite.creneauhoraire;
import org.example.sport.repositories.CreneauHoraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CreneauHoraireService {

    @Autowired
    private CreneauHoraireRepository creneauHoraireRepository;

    public List<creneauhoraire> getAllCreneaux() {
        return creneauHoraireRepository.findAll();
    }

    public Optional<creneauhoraire> getCreneauById(Long id) {
        return creneauHoraireRepository.findById(id);
    }

    public creneauhoraire addCreneau(creneauhoraire creneau) {
        return creneauHoraireRepository.save(creneau);
    }

    public void deleteCreneau(Long id) {
        creneauHoraireRepository.deleteById(id);
    }
}
