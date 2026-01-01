package com.example.backend.controller;

import com.example.backend.domain.NightlyPerson;
import com.example.backend.service.NightlyPersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/nightly")
public class NightlyPersonController {

    private final NightlyPersonService service;

    public NightlyPersonController(NightlyPersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<NightlyPerson> getAll() {
        return service.findAll();
    }

    @PostMapping
    public NightlyPerson create(@RequestBody NightlyPerson ap) {
        return service.create(ap);
    }

    @PutMapping("/{id}")
    public NightlyPerson update(
        @PathVariable UUID id,
        @RequestBody NightlyPerson ap
    ) {
        return service.update(id, ap);
    }
}
