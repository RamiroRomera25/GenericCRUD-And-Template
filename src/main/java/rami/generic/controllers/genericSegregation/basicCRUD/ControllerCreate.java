package rami.generic.controllers.genericSegregation.basicCRUD;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;

public interface ControllerCreate<E, I, M, DTOPOST> {

    GenericCreate<E, I, M, DTOPOST> getService();

    @PostMapping("")
    default ResponseEntity<M> create(@RequestBody @Valid DTOPOST dtoPost) {
        return ResponseEntity.ok(getService().create(dtoPost));
    }
}
