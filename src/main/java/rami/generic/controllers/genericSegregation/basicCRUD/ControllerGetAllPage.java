package rami.generic.controllers.genericSegregation.basicCRUD;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllPage;

/**
 * Generic interface for retrieving paginated entities through REST controllers.
 *
 * @param <E> the entity type
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <SERVICE> the service interface that implements the retrieval logic
 */
public interface ControllerGetAllPage<E, I, M, SERVICE extends GenericGetAllPage<E, I, M>> {

    /**
     * Gets the service instance for paginated retrieval.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Retrieves a paginated list of entities.
     *
     * @param page the page number to retrieve (default is 0)
     * @param size the number of items per page (default is 10)
     * @param sortBy the field to sort by (default is "id")
     * @param isAscending whether the sorting order is ascending (default is true)
     * @return a paginated response containing the retrieved entities
     */
    @Operation(
            summary = "Get a paginated list of entities",
            description = "Retrieve entities in a paginated format based on the provided criteria."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entities successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content available",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping("/page")
    default ResponseEntity<Page<M>> getAll(@RequestParam(required = false, defaultValue = "0") int page,
                                           @RequestParam(required = false, defaultValue = "10") int size,
                                           @RequestParam(required = false, defaultValue = "id") String sortBy,
                                           @RequestParam(required = false, defaultValue = "true") boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return ResponseEntity.ok(getService().getAll(PageRequest.of(page, size, Sort.by(direction, sortBy))));
    }
}
