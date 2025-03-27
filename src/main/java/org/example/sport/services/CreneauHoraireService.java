package org.example.sport.services;

import org.example.sport.entite.CreneauHoraire;
import org.example.sport.repositories.CreneauHoraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CreneauHoraireService {

    @Autowired
    private CreneauHoraireRepository creneauHoraireRepository;

    public List<CreneauHoraire> getAllCreneaux() {
        return creneauHoraireRepository.findAll();
    }

    public Optional<CreneauHoraire> getCreneauById(Long id) {
        return creneauHoraireRepository.findById(id);
    }

    public CreneauHoraire addCreneau(CreneauHoraire creneau) {
        return creneauHoraireRepository.save(creneau);
    }

    public void deleteCreneau(Long id) {
        creneauHoraireRepository.deleteById(id);
    }
}
