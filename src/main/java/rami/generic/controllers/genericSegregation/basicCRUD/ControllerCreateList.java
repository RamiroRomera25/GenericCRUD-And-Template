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
import rami.generic.services.genericSegregation.basicCRUD.GenericCreateList;

import java.util.List;

/**
 * Generic interface for creating a list of entities through REST controllers.
 *
 * @param <E> the entity type
 * @param <I> the ID type for the entity
 * @param <M> the model type to return
 * @param <DTOPOST> the DTO type used for creating entities
 * @param <SERVICE> the service interface that implements the creation logic
 */
public interface ControllerCreateList<E, I, M, DTOPOST, SERVICE extends GenericCreateList<E, I, M, DTOPOST>> {

    /**
     * Gets the service instance for creating a list of entities.
     *
     * @return the service instance
     */
    SERVICE getService();

    /**
     * Creates a list of new entities from the provided DTOs.
     *
     * @param dtoPost the list of DTOs containing the data for the new entities
     * @return the created model representation of the entities
     */
    @Operation(
            summary = "Create a list of new entities",
            description = "Create multiple new entities based on the provided data and return the created models."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Entities successfully created"
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
    @PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    default ResponseEntity<M> create(@RequestBody @Valid List<DTOPOST> dtoPost) {
        return ResponseEntity.ok(getService().createList(dtoPost));
    }
}