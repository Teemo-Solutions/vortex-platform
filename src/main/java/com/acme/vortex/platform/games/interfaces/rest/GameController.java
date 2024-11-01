package com.acme.vortex.platform.games.interfaces.rest;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import com.acme.vortex.platform.games.domain.services.GameCommandService;
import com.acme.vortex.platform.games.interfaces.rest.resources.CreateGameResource;
import com.acme.vortex.platform.games.interfaces.rest.resources.GameResource;
import com.acme.vortex.platform.games.interfaces.rest.transform.CreateGameCommandFromResourceAssembler;
import com.acme.vortex.platform.games.interfaces.rest.transform.GameResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/games", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Games", description = "Operations related to games")
public class GameController {
    private final GameCommandService gameCommandService;

    public GameController(GameCommandService gameCommandService) {
        this.gameCommandService = gameCommandService;
    }

    @Operation(
            summary = "Create a game",
            description = "Create a game with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<GameResource> createGame(@RequestBody CreateGameResource resource) {
        Optional<Game> game = gameCommandService
                .handle(CreateGameCommandFromResourceAssembler.toCommand(resource));
        return game.map(g -> new ResponseEntity<>(GameResourceFromEntityAssembler.toResourceFromEntity(g), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}