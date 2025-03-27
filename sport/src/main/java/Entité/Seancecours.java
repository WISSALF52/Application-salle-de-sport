package Entité;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Seancecours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cours cours;

    private LocalDateTime dateHeure;
    private int duree;

    @ManyToMany
    private List<Membre> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public List<Membre> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Membre> participants) {
        this.participants = participants;
    }


}
