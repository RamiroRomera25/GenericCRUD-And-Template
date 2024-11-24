package rami.generic.controllers.genericSegregation.basicCRUD;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetById;

public interface ControllerGetById<E, I, M> {

    GenericGetById<E, I, M> getService();

    @GetMapping("/{id}")
    default ResponseEntity<M> getAll(@PathVariable I id) {
        return ResponseEntity.ok(getService().getModelById(id));
    }
}
