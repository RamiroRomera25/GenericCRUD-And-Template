package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.services.genericSegregation.basicCRUD.ServiceUpdate;

/**
 * Generic interface for updating an entity through a REST controller.
 *
 * @param <E> the entity type
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <DTOPUT> the DTO type for updating the entity
 * @param <SERVICE> the service interface that implements the update logic
 */
public interface ControllerUpdate<E, I, M, DTOPUT, SERVICE extends ServiceUpdate<E, I, M, DTOPUT>> {

    /**
     * Gets the service instance for updating an entity.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Updates an entity identified by the given ID with the provided DTO.
     *
     * @param id the ID of the entity to update
     * @param dtoPut the DTO containing the data to update the entity with
     * @return a response containing the updated model
     */
    @Operation(
            summary = "Update an entity by ID",
            description = "Updates an entity identified by the provided ID using the given DTO. Returns the updated model."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entity successfully updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entity not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PutMapping("/{id}")
    default ResponseEntity<M> update(@PathVariable I id,
                                     @RequestBody @Valid DTOPUT dtoPut) {
        return ResponseEntity.ok(getService().update(dtoPut, id));
    }
}
