package rami.generic.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rami.generic.dtos.common.ErrorApi;

@RestController
@RequestMapping("")
@Slf4j
public class PingController {

    private static final Logger logger = LoggerFactory.getLogger(PingController.class);

    /**
     * The health check method.
     * @return the word pong
     */
    @Operation(
            summary = "Check healthy of the app",
            description = "If the app it's alive response pong")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @GetMapping("/ping")
    public String ping() {
        for (int i = 0; i < 100; i++) {
            logger.error("PING");
            logger.warn("HOLA");
        }
        return "pong";
    }
}
