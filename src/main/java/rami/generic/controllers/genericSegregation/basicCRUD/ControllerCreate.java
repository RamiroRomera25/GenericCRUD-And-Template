package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;

public interface ControllerCreate<E, I, M, DTOPOST, SERVICE extends GenericCreate<E, I, M, DTOPOST>> {

    SERVICE getService();
    @Operation(summary = "Create a new entity", description = "Create a new entity and return the created entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entity successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    default ResponseEntity<M> create(@RequestBody @Valid DTOPOST dtoPost) {
        return ResponseEntity.ok(getService().create(dtoPost));
    }
}
