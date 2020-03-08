package org.dsacleveland.evictiontracker.controller.rest;

import org.dsacleveland.evictiontracker.service.type.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

public abstract class AbstractRestController<T, SVC extends EntityService<T, UUID>> {

    protected SVC entityService;

    @Autowired
    public AbstractRestController(SVC entityService) {
        this.entityService = entityService;
    }

    @PostMapping("")
    public ResponseEntity<T> create(@RequestBody T dto) {
        return status(HttpStatus.CREATED).body(this.entityService.create(dto));
    }

    @GetMapping("")
    public List<T> getAll() {
        return this.entityService.readAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getOne(@PathVariable UUID id) {
        return ok(this.entityService.readOne(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Object with ID " + id + " not found")
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable UUID id, @RequestBody T dto) {
        return ok(this.entityService.update(id, dto).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Could not update object with ID " + id + ", object not found")
        ));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.entityService.delete(id);
    }
}
