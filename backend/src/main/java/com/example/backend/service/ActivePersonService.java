package com.example.backend.service;

import com.example.backend.domain.ActivePerson;
import com.example.backend.repo.ActivePersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ActivePersonService {

    private final ActivePersonRepository repo;

    public ActivePersonService(ActivePersonRepository repo) {
        this.repo = repo;
    }

    public ActivePerson create(ActivePerson ap) {
        return repo.save(ap);
    }

    public List<ActivePerson> findAll() {
        return repo.findAll();
    }

    public ActivePerson update(UUID id, ActivePerson update) {
        return repo.findById(id).map(ap -> {
            ap.setFirstName(update.getFirstName());
            ap.setLastName(update.getLastName());
            ap.setGuessedCorrectly(update.isGuessedCorrectly());
            return repo.save(ap);
        }).orElseThrow();
    }
}
