package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.entities.base.BaseEntity;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDeleteList;

import java.util.List;

/**
 * Generic interface for soft deleting and reactivating a list of entities through REST controllers.
 *
 * @param <E> the entity type, extending BaseEntity
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <SERVICE> the service interface that implements the soft delete and reactivation logic
 */
public interface ControllerSoftDeleteList<E extends BaseEntity, I, M, SERVICE extends GenericSoftDeleteList<E, I, M>> {

    /**
     * Gets the service instance for soft delete and reactivation of a list of entities.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Soft deletes a list of entities by their IDs.
     *
     * @param ids the list of IDs of the entities to delete
     * @return a response indicating success or failure
     */
    @Operation(
            summary = "Soft delete a list of entities",
            description = "Soft deletes the entities identified by the provided list of IDs, marking them as inactive. Returns a 404 status if any entity is not found."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entities successfully soft deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "One or more entities not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @DeleteMapping("/list")
    default ResponseEntity<List<M>> delete(@RequestBody List<I> ids) {
        return ResponseEntity.ok(getService().delete(ids));
    }

    /**
     * Reactivates a list of entities by their IDs.
     *
     * @param ids the list of IDs of the entities to reactivate
     * @return a response indicating success or failure
     */
    @Operation(
            summary = "Reactivate a list of entities",
            description = "Reactivates the entities identified by the provided list of IDs, marking them as active again. Returns a 404 status if any entity is not found."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entities successfully reactivated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "One or more entities not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PatchMapping("/reactivate/list")
    default ResponseEntity<List<M>> reactivate(@RequestBody List<I> ids) {
        return ResponseEntity.ok(getService().reactivate(ids));
    }
}
