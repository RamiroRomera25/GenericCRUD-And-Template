package rami.generic.controllers.genericSegregation.basicCRUD;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;

public interface ControllerCreate<E, I, M, DTOPOST, SERVICE extends GenericCreate<E, I, M, DTOPOST>> {

    SERVICE getService();

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    default ResponseEntity<M> create(@RequestBody @Valid DTOPOST dtoPost) {
        return ResponseEntity.ok(getService().create(dtoPost));
    }
}
