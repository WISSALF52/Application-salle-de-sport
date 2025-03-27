package Service;

import Entité.Seancecours;
import Repository.SeancecoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceSeancecours {
    @Autowired
    private SeancecoursRepository seanceCoursRepository;

    public Seancecours planifierSeance(Seancecours seance) {
        return seanceCoursRepository.save(seance);
    }
}
