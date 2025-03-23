package org.example.sport.controllers;

import org.example.sport.entite.ServiceSport;
import org.example.sport.services.ServiceSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceSportController {

    @Autowired
    private ServiceSportService serviceSportService;

    @GetMapping
    public List<ServiceSport> getAllServices() {
        return serviceSportService.getAllServices();
    }

    @GetMapping("/{id}")
    public Optional<ServiceSport> getServiceById(@PathVariable Long id) {
        return serviceSportService.getServiceById(id);
    }

    @PostMapping
    public ServiceSport addService(@RequestBody ServiceSport service) {
        return serviceSportService.addService(service);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceSportService.deleteService(id);
    }
}
