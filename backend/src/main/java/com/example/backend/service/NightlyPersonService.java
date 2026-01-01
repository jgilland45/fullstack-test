package com.example.backend.service;

import com.example.backend.domain.NightlyPerson;
import com.example.backend.repo.NightlyPersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NightlyPersonService {

    private final NightlyPersonRepository repo;

    public NightlyPersonService(NightlyPersonRepository repo) {
        this.repo = repo;
    }

    public NightlyPerson create(NightlyPerson ap) {
        return repo.save(ap);
    }

    public List<NightlyPerson> findAll() {
        return repo.findAll();
    }

    public NightlyPerson update(UUID id, NightlyPerson update) {
        return repo.findById(id).map(ap -> {
            ap.setFirstName(update.getFirstName());
            ap.setLastName(update.getLastName());
            return repo.save(ap);
        }).orElseThrow();
    }
}
