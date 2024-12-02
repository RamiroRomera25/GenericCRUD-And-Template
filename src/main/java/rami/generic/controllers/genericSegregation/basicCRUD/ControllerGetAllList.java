package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.services.genericSegregation.basicCRUD.ServiceGetAllList;

import java.util.List;

/**
 * Generic interface for retrieving all entities through REST controllers.
 *
 * @param <E> the entity type
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <SERVICE> the service interface that implements the retrieval logic
 */
public interface ControllerGetAllList<E, I, M, SERVICE extends ServiceGetAllList<E, I, M>> {

    /**
     * Gets the service instance for retrieving entities.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Retrieves all entities with optional sorting parameters.
     *
     * @param sortBy the field to sort by
     * @param isAscending whether to sort in ascending order
     * @return the list of entities sorted as specified
     */
    @Operation(
            summary = "Retrieve all entities",
            description = "Retrieve all entities from the repository with optional sorting parameters."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entities retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content available",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping("")
    default ResponseEntity<List<M>> getAll(@RequestParam(required = false, defaultValue = "id") String sortBy,
                                           @RequestParam(required = false, defaultValue = "true") boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return ResponseEntity.ok(getService().getAll(Sort.by(direction, sortBy)));
    }
}
