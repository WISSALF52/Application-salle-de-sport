package org.example.sport.entite;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class danse extends ServiceSport {
    private String styleDanse;
    private Double prix;
}

