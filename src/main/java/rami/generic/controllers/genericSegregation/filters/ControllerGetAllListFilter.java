package rami.generic.controllers.genericSegregation.filters;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import rami.generic.dtos.common.ErrorApi;
import rami.generic.services.genericSegregation.filters.GenericGetAllListFilter;

import java.util.List;

/**
 * Generic interface for retrieving a filtered list of entities through a REST controller.
 *
 * @param <E> the entity type
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <DTOFILTER> the DTO type for filtering the entities
 * @param <SERVICE> the service interface that implements the retrieval logic with filters
 */
public interface ControllerGetAllListFilter<E, I, M, DTOFILTER, SERVICE extends GenericGetAllListFilter<E, I, M, DTOFILTER>> {

    /**
     * Gets the service instance for filtered retrieval of entities.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Retrieves a list of entities based on the provided filter and sorting criteria.
     *
     * @param filters the DTO containing the filter criteria
     * @param sortBy the field to sort by (default is "id")
     * @param isAscending whether the sorting order is ascending (default is true)
     * @return a response containing the filtered list of entities
     */
    @Operation(
            summary = "Get a filtered list of entities",
            description = "Retrieves a list of entities based on the given filter and sorting criteria."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Entities successfully retrieved"
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
    @GetMapping("/filters")
    default ResponseEntity<List<M>> getAllFilter(@RequestBody DTOFILTER filters,
                                                 @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                 @RequestParam(required = false, defaultValue = "true") boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return ResponseEntity.ok(getService().getAll(filters, Sort.by(direction, sortBy)));
    }
}

