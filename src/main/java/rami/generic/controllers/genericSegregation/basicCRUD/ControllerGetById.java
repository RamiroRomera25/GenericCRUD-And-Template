package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetById;

public interface ControllerGetById<E, I, M, SERVICE extends GenericGetById<E, I, M>> {

    SERVICE getService();

    @GetMapping("/{id}")
    default ResponseEntity<M> getAll(@PathVariable I id) {
        return ResponseEntity.ok(getService().getModelById(id));
    }
}
