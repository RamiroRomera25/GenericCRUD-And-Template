package rami.generic.controllers.genericSegregation.filters;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.services.genericSegregation.filters.GenericGetAllPageFilter;

/**
 * Generic interface for controllers that retrieve a paginated and filtered list of entities.
 *
 * @param <E> the entity type
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <DTOFILTER> the DTO type used for filtering the entities
 */
public interface ControllerGetAllPageFilter<E, I, M, DTOFILTER, SERVICE extends GenericGetAllPageFilter<E, I, M, DTOFILTER>> {

    /**
     * Retrieves the service instance for handling the operation.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Retrieves a paginated and filtered list of entities.
     *
     * @param filters the DTO containing filter criteria
     * @param page the page number (zero-based)
     * @param size the number of items per page
     * @param sortBy the field to sort by
     * @param isAscending indicates whether the sorting should be in ascending order
     * @return a paginated and filtered list of entities
     */
    @Operation(
            summary = "Retrieve a paginated and filtered list of entities",
            description = "Retrieves a paginated and filtered list of entities based on the provided filters and pagination details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entities successfully retrieved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No content found matching the filter",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping("/page/filters")
    default ResponseEntity<Page<M>> getAllFilter(@RequestBody DTOFILTER filters,
                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                 @RequestParam(required = false, defaultValue = "true") boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return ResponseEntity.ok(getService().getAll(PageRequest.of(page, size, Sort.by(direction, sortBy)), filters));
    }
}
