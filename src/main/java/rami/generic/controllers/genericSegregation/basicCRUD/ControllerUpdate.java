package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.services.genericSegregation.basicCRUD.GenericUpdate;

public interface ControllerUpdate<E, I, M, DTOPUT, SERVICE extends GenericUpdate<E, I, M, DTOPUT>> {

    SERVICE getService();

    @PutMapping("/{id}")
    default ResponseEntity<M> update(@PathVariable I id,
                                     @RequestBody @Valid DTOPUT dtoPut) {
        return ResponseEntity.ok(getService().update(dtoPut, id));
    }
}
