package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.services.genericSegregation.basicCRUD.ServiceGetById;

/**
 * Generic interface for retrieving an entity by its ID through REST controllers.
 *
 * @param <E> the entity type
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <SERVICE> the service interface that implements the retrieval logic
 */
public interface ControllerGetById<E, I, M, SERVICE extends ServiceGetById<E, I, M>> {

    /**
     * Gets the service instance for entity retrieval by ID.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Retrieves an entity by its ID.
     *
     * @param id the ID of the entity to retrieve
     * @return a response containing the model of the entity if found
     */
    @Operation(
            summary = "Retrieve an entity by its ID",
            description = "Retrieves a model representation of an entity using the provided ID.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "The ID of the entity to be retrieved",
                            required = true
                    )
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entity successfully retrieved"
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
    @GetMapping("/{id}")
    default ResponseEntity<M> getAll(@PathVariable I id) {
        return ResponseEntity.ok(getService().getModelById(id));
    }
}
