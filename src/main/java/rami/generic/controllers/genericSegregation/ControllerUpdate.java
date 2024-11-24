package rami.generic.controllers.genericSegregation;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.services.genericSegregation.basicCRUD.GenericUpdate;

public interface ControllerUpdate<E, I, M, DTOPUT> {

    GenericUpdate<E, I, M, DTOPUT> getService();

    @PutMapping("/{id}")
    default ResponseEntity<M> update(@PathVariable I id,
                                     @RequestBody @Valid DTOPUT dtoPut) {
        return ResponseEntity.ok(getService().update(dtoPut, id));
    }
}
