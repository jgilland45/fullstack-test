package com.example.backend.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "active_person")
public class ActivePerson {

    @Id
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "guessed_correctly", nullable = false)
    private boolean guessedCorrectly;

    protected ActivePerson() {
        // JPA only
    }

    public ActivePerson(String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.guessedCorrectly = false;
    }

    @PrePersist
    void ensureId() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isGuessedCorrectly() {
        return guessedCorrectly;
    }

    public void setGuessedCorrectly(boolean guessedCorrectly) {
        this.guessedCorrectly = guessedCorrectly;
    }
}
