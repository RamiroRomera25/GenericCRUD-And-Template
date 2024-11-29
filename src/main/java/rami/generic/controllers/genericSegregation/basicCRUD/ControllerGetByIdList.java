package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetByIdList;

import java.util.List;

/**
 * Generic interface for retrieving entities by its ID through REST controllers.
 *
 * @param <E> the entity type
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <SERVICE> the service interface that implements the retrieval logic
 */
public interface ControllerGetByIdList<E, I, M, SERVICE extends GenericGetByIdList<E, I, M>> {

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
            description = "Retrieves a model representation of entities using the provided ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entities successfully retrieved"
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
    @PostMapping("")
    default ResponseEntity<M> getAll(@RequestBody List<I> ids) {
        return ResponseEntity.ok(getService().getModelByIdList(ids));
    }
}
