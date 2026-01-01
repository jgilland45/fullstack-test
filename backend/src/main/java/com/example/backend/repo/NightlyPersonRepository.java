package com.example.backend.repo;

import com.example.backend.domain.NightlyPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NightlyPersonRepository
        extends JpaRepository<NightlyPerson, UUID> {
}
