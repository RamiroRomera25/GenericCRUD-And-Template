package ar.edu.utn.frc.tup.lciii.templateSpring.controllers;

import ar.edu.utn.frc.tup.lciii.templateSpring.services.GenericCRUDService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<E, I, M, DTOPOST, DTOPUT> {

    public abstract GenericCRUDService<E, I, M, DTOPOST, DTOPUT> getService();

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(required = false) Integer page,
                                    @RequestParam(required = false) Integer size) {
        if (page != null && size != null) {
            return ResponseEntity.ok(getService().getAll(PageRequest.of(page, size)));
        } else {
            return ResponseEntity.ok(getService().getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<M> getById(@PathVariable I id) {
        M entity = getService().getById(id);
        return ResponseEntity.ok(entity);
    }

    @PostMapping("")
    public ResponseEntity<M> create(@RequestBody @Valid DTOPOST dtoPost) {
        M createdEntity = getService().create(dtoPost);
        return ResponseEntity.ok(createdEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<M> update(@RequestBody @Valid DTOPUT dtoPut, @PathVariable I id) {
        M updatedEntity = getService().update(dtoPut, id);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<M> delete(@PathVariable I id) {
        M deletedEntity = getService().delete(id);
        return ResponseEntity.ok(deletedEntity);
    }

    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<M> reactivate(@PathVariable I id) {
        M reactivatedEntity = getService().reactivate(id);
        return ResponseEntity.ok(reactivatedEntity);
    }
}
