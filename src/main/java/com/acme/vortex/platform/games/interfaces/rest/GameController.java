package com.acme.vortex.platform.games.interfaces.rest;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import com.acme.vortex.platform.games.domain.model.commands.DeleteGameCommand;
import com.acme.vortex.platform.games.domain.model.queries.GetAllGamesByTitleQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetAllGamesQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetGameByIdQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetGameByTitleAndDeveloperQuery;
import com.acme.vortex.platform.games.domain.services.GameCommandService;
import com.acme.vortex.platform.games.domain.services.GameQueryService;
import com.acme.vortex.platform.games.interfaces.rest.resources.CreateGameResource;
import com.acme.vortex.platform.games.interfaces.rest.resources.GameResource;
import com.acme.vortex.platform.games.interfaces.rest.resources.UpdateGameResource;
import com.acme.vortex.platform.games.interfaces.rest.transform.CreateGameCommandFromResourceAssembler;
import com.acme.vortex.platform.games.interfaces.rest.transform.GameResourceFromEntityAssembler;
import com.acme.vortex.platform.games.interfaces.rest.transform.UpdateGameCommandFromResourceAssembler;
import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/games", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Games", description = "Operations related to games")
public class GameController {
    private final GameCommandService gameCommandService;
    private final GameQueryService gameQueryService;

    public GameController(GameCommandService gameCommandService, GameQueryService gameQueryService) {
        this.gameCommandService = gameCommandService;
        this.gameQueryService = gameQueryService;
    }

    // post a game
    /**
     * Create a game
     *
     * @param resource The {@link CreateGameResource} instance
     * @return The {@link GameResource} resource for the created game
     */
    @PostMapping
    @Operation(
            summary = "Create a game",
            description = "Create a game with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    public ResponseEntity<GameResource> createGame(@RequestBody CreateGameResource resource) {
        Optional<Game> game = gameCommandService
                .handle(CreateGameCommandFromResourceAssembler.toCommand(resource));
        return game.map(g -> new ResponseEntity<>(GameResourceFromEntityAssembler.toResourceFromEntity(g), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Get a game by ID
     * @param id The game id
     * @return The {@link GameResource} resource for the game
     */
    @GetMapping("{id}")
    @Operation(
            summary = "Get a game by ID",
            description = "Get a game by the provided ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game found"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
    })
    public ResponseEntity<GameResource> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameQueryService.handle(new GetGameByIdQuery(id));
        return game.map(g -> ResponseEntity.ok(GameResourceFromEntityAssembler.toResourceFromEntity(g)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // get all games
    /**
     * Get games with parameters
     *
     * @return The list of {@link GameResource} resources
     */
    @GetMapping
    @Operation(
            summary = "Get games with parameters",
            description = "Get games with the provided parameters"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Games found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    public ResponseEntity<List<GameResource>> getAllGames(){
        var games = gameQueryService.handle(new GetAllGamesQuery());
        if (games.isEmpty()) return ResponseEntity.notFound().build();
        var gameResources = games.stream()
                .map(GameResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(gameResources);
    }

    private ResponseEntity<List<GameResource>> getAllGamesByTitle(String title) {
        var getAllGamesByTitleQuery = new GetAllGamesByTitleQuery(title);
        var games = gameQueryService.handle(getAllGamesByTitleQuery);
        if (games.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var gameResources = games.stream()
                .map(GameResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(gameResources);
    }

    private ResponseEntity<GameResource> getGameByTitleAndDeveloper(String title, String developer) {
        var getGameByTitleAndDeveloperQuery = new GetGameByTitleAndDeveloperQuery(title, developer);
        var game = gameQueryService.handle(getGameByTitleAndDeveloperQuery);
        return game.map(g -> ResponseEntity.ok(GameResourceFromEntityAssembler.toResourceFromEntity(g)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update a game
     *
     * @param gameId The game id
     * @param resource The {@link UpdateGameResource} instance
     * @return The {@link GameResource} resource for the updated game
     */
    @PutMapping("/{gameId}")
    @Operation(
            summary = "Update a game",
            description = "Update a game with the provided details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game updated"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
    })
    public ResponseEntity<GameResource> updateGame(@PathVariable Long gameId, @RequestBody UpdateGameResource resource){
        var updateGameCommand = UpdateGameCommandFromResourceAssembler.ToCommandFromResource(gameId, resource);
        var updatedGame = gameCommandService.handle(updateGameCommand);
        if (updatedGame.isEmpty()) return ResponseEntity.notFound().build();
        var updatedGameEntity = updatedGame.get();
        var updatedGameResource = GameResourceFromEntityAssembler.toResourceFromEntity(updatedGameEntity);
        return ResponseEntity.ok(updatedGameResource);
    }

    /**
     * Delete a game
     *
     * @param gameId The game id
     * @return The response entity
     */
    @DeleteMapping("/{gameId}")
    @Operation(
            summary = "Delete a game",
            description = "Delete a game with the provided ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Game deleted"),
            @ApiResponse(responseCode = "404", description = "Game not found"),
    })
    public ResponseEntity<?> deleteGame(@PathVariable Long gameId) {
        var deleteGameCommand = new DeleteGameCommand(gameId);
        gameCommandService.handle(deleteGameCommand);
        return ResponseEntity.ok("Game with given id successfully deleted");
    }
}