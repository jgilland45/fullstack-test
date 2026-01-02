package com.example.backend.repo;

import com.example.backend.domain.ActivePerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ActivePersonRepository extends JpaRepository<ActivePerson, UUID> {
}