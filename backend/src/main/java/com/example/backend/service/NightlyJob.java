package com.example.backend.service;

import com.example.backend.domain.NightlyPerson;
import com.example.backend.repo.NightlyPersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NightlyJob {

    private final NightlyPersonRepository repo;

    public NightlyJob(NightlyPersonRepository repo) {
        this.repo = repo;
    }

    public void runImport(List<ExternalPersonDTO> results) {
        results.forEach(dto -> {
            NightlyPerson np = new NightlyPerson(dto.firstName(), dto.lastName());
            repo.save(np);
        });
    }
}
