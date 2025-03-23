package org.example.sport.services;

import org.example.sport.entite.ServiceSport;
import org.example.sport.repositories.ServiceSportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceSportService {

    @Autowired
    private ServiceSportRepository serviceSportRepository;

    public List<ServiceSport> getAllServices() {
        return serviceSportRepository.findAll();
    }

    public Optional<ServiceSport> getServiceById(Long id) {
        return serviceSportRepository.findById(id);
    }

    public ServiceSport addService(ServiceSport s) {
        return serviceSportRepository.save(s);
    }

    public void deleteService(Long id) {
        serviceSportRepository.deleteById(id);
    }
}
