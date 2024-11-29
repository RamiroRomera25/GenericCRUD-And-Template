package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.entities.base.BaseEntity;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDelete;

/**
 * Generic interface for soft deleting and reactivating entities through REST controllers.
 *
 * @param <E> the entity type, extending BaseEntity
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <SERVICE> the service interface that implements the soft delete and reactivate logic
 */
public interface ControllerSoftDelete<E extends BaseEntity, I, M, SERVICE extends GenericSoftDelete<E, I, M>> {

    /**
     * Gets the service instance for soft delete and reactivation.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Soft deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     * @return a response indicating success or failure
     */
    @Operation(
            summary = "Soft delete an entity by ID",
            description = "Soft deletes the entity identified by the provided ID, marking it as inactive. Returns a 404 status if not found."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entity successfully soft deleted"
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
    @DeleteMapping("/{id}")
    default ResponseEntity<M> delete(@PathVariable I id) {
        return ResponseEntity.ok(getService().delete(id));
    }

    /**
     * Reactivates an entity by its ID.
     *
     * @param id the ID of the entity to reactivate
     * @return a response indicating success or failure
     */
    @Operation(
            summary = "Reactivate an entity by ID",
            description = "Reactivates the entity identified by the provided ID, marking it as active again. Returns a 404 status if not found."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entity successfully reactivated"
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
    @PatchMapping("/{id}/reactivate")
    default ResponseEntity<M> reactivate(@PathVariable I id) {
        return ResponseEntity.ok(getService().reactivate(id));
    }
}
