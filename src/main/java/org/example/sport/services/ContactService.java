package org.example.sport.services;

import org.example.sport.entite.Contact;
import org.example.sport.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // Méthode pour sauvegarder un message de contact
    public Contact saveContactMessage(Contact contact) {
        return contactRepository.save(contact); // Sauvegarde dans la base de données
    }
}
