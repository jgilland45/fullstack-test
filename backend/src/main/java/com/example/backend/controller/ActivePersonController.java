package com.example.backend.controller;

import com.example.backend.domain.ActivePerson;
import com.example.backend.service.ActivePersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/active")
public class ActivePersonController {

    private final ActivePersonService service;

    public ActivePersonController(ActivePersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<ActivePerson> getAll() {
        return service.findAll();
    }

    @PostMapping
    public ActivePerson create(@RequestBody ActivePerson ap) {
        return service.create(ap);
    }

    @PutMapping("/{id}")
    public ActivePerson update(
        @PathVariable UUID id,
        @RequestBody ActivePerson ap
    ) {
        return service.update(id, ap);
    }
}
