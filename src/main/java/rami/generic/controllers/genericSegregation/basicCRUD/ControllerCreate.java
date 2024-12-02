package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.services.genericSegregation.basicCRUD.ServiceCreate;

/**
 * Generic interface for creating entities through REST controllers.
 *
 * @param <E> the entity type
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <DTOPOST> the DTO type used for creating entities
 * @param <SERVICE> the service interface that implements the creation logic
 */
public interface ControllerCreate<E, I, M, DTOPOST, SERVICE extends ServiceCreate<E, I, M, DTOPOST>> {

    /**
     * Gets the service instance for entity creation.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Creates a new entity from the provided DTO.
     *
     * @param dtoPost the DTO containing the data for the new entity
     * @return the created model representation of the entity
     */
    @Operation(
            summary = "Create a new entity",
            description = "Create a new entity based on the provided data and return the created model."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Entity successfully created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    default ResponseEntity<M> create(@RequestBody @Valid DTOPOST dtoPost) {
        return ResponseEntity.ok(getService().create(dtoPost));
    }
}
