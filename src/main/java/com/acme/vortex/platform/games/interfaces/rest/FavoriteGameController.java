package com.acme.vortex.platform.games.interfaces.rest;

import com.acme.vortex.platform.games.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.games.domain.model.queries.GetAllFavoriteGamesByNameQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetFavoriteGameByIdQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetFavoriteGameByNameAndImageQuery;
import com.acme.vortex.platform.games.domain.services.FavoriteGameCommandService;
import com.acme.vortex.platform.games.domain.services.FavoriteGameQueryService;
import com.acme.vortex.platform.games.interfaces.rest.resources.CreateFavoriteGameResource;
import com.acme.vortex.platform.games.interfaces.rest.resources.FavoriteGameResource;
import com.acme.vortex.platform.games.interfaces.rest.transform.CreateFavoriteGameCommandFromResourceAssembler;
import com.acme.vortex.platform.games.interfaces.rest.transform.FavoriteGameResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/favorite-games", produces = APPLICATION_JSON_VALUE)
@Tag(name="Favorite Games", description = "Operation related to favorite games")
public class FavoriteGameController {
    private final FavoriteGameCommandService favoriteGameCommandService;
    private final FavoriteGameQueryService favoriteGameQueryService;

    /**
     * Constructor
     * @param favoriteGameQueryService FavoriteGameQueryService
     * @param favoriteGameCommandService FavoriteGameCommandService
     */
    public FavoriteGameController(FavoriteGameCommandService favoriteGameCommandService, FavoriteGameQueryService favoriteGameQueryService) {
        this.favoriteGameCommandService = favoriteGameCommandService;
        this.favoriteGameQueryService = favoriteGameQueryService;
    }

    @Operation(
            summary = "Create a favorite game",
            description = "Create a favorite game with the provided name and image"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorite game created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<FavoriteGameResource> createFavoriteSource(@RequestBody CreateFavoriteGameResource resource) {
        Optional<FavoriteGame> favoriteSource = favoriteGameCommandService
                .handle(CreateFavoriteGameCommandFromResourceAssembler.toCommand(resource));
        return favoriteSource.map(source -> new ResponseEntity<>(FavoriteGameResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<List<FavoriteGameResource>> getAllFavoriteGamesByName(String name) {
        var getAllFavoriteGamesByNameQuery = new GetAllFavoriteGamesByNameQuery(name);
        var favoriteGames = favoriteGameQueryService.handle(getAllFavoriteGamesByNameQuery);
        if (favoriteGames.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var favoriteGameResources = favoriteGames.stream()
                .map(FavoriteGameResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(favoriteGameResources);
    }

    private ResponseEntity<FavoriteGameResource> getFavoriteGameByNameAndImage(String name, String image) {
        var getFavoriteGameByNameAndImageQuery = new GetFavoriteGameByNameAndImageQuery(name, image);
        var favoriteGame = favoriteGameQueryService.handle(getFavoriteGameByNameAndImageQuery);
        if (favoriteGame.isEmpty()) return ResponseEntity.notFound().build();
        return favoriteGame.map(game -> ResponseEntity.ok(FavoriteGameResourceFromEntityAssembler.toResourceFromEntity(game)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get a favorite game by ID",
            description = "Get a favorite game by the provided ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite game found"),
            @ApiResponse(responseCode = "404", description = "Favorite game not found"),
    })
    @GetMapping({"{id}"})
    private ResponseEntity<FavoriteGameResource> getFavoriteGameById(@PathVariable Long id) {
        Optional<FavoriteGame> favoriteGame = favoriteGameQueryService.handle(new GetFavoriteGameByIdQuery(id));
        return favoriteGame.map(game -> ResponseEntity.ok(FavoriteGameResourceFromEntityAssembler.toResourceFromEntity(game)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get a favorite game with parameters",
            description = "Get a favorite game with the provided parameters"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite game found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @GetMapping
    public ResponseEntity<?> getFavoriteGameWithParameters(@Parameter(name = "params", hidden = true)
                                                               @RequestParam Map<String, String> params) {
        if (params.containsKey("name") && params.containsKey("image")) {
            return getFavoriteGameByNameAndImage(params.get("name"), params.get("image"));
        } else if (params.containsKey("name")) {
            return getAllFavoriteGamesByName(params.get("name"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}