package com.acme.vortex.platform.favoriteGames.interfaces.rest;

import com.acme.vortex.platform.favoriteGames.domain.model.commands.DeleteFavoriteGameCommand;
import com.acme.vortex.platform.favoriteGames.domain.model.queries.GetFavoriteGameByIdQuery;
import com.acme.vortex.platform.favoriteGames.domain.services.FavoriteGameCommandService;
import com.acme.vortex.platform.favoriteGames.domain.services.FavoriteGameQueryService;
import com.acme.vortex.platform.favoriteGames.interfaces.rest.resources.CreateFavoriteGameResource;
import com.acme.vortex.platform.favoriteGames.interfaces.rest.resources.FavoriteGameResource;
import com.acme.vortex.platform.favoriteGames.interfaces.rest.transform.CreateFavoriteGameCommandFromResourceAssembler;
import com.acme.vortex.platform.favoriteGames.interfaces.rest.transform.FavoriteGameResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/favorite-games", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Favorite Games", description = "Available Favorite Games Endpoints")
public class FavoriteGameController {
    private final FavoriteGameCommandService favoriteGameCommandService;
    private final FavoriteGameQueryService favoriteGameQueryService;

    /**
     * Constructor
     *
     * @param favoriteGameCommandService The {@link FavoriteGameCommandService} instance
     * @param favoriteGameQueryService   The {@link FavoriteGameQueryService} instance
     */
    public FavoriteGameController(FavoriteGameCommandService favoriteGameCommandService, FavoriteGameQueryService favoriteGameQueryService) {
        this.favoriteGameCommandService = favoriteGameCommandService;
        this.favoriteGameQueryService = favoriteGameQueryService;
    }

    /**
     * Create a new favorite game
     *
     * @param resource The {@link CreateFavoriteGameResource} instance
     * @return The {@link FavoriteGameResource} resource for the created favorite game
     */
    @PostMapping
    @Operation(summary = "Create a new favorite game", description = "Create a new favorite game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorite game created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Favorite game already exists")
    })
    public ResponseEntity<FavoriteGameResource> createFavoriteGame(@RequestBody CreateFavoriteGameResource resource) {
        var createFavoriteGameCommand = CreateFavoriteGameCommandFromResourceAssembler.toCommandFromResource(resource);
        var favoriteGameId = favoriteGameCommandService.handle(createFavoriteGameCommand);
        if (favoriteGameId == null || favoriteGameId == 0L) return ResponseEntity.badRequest().build();
        var getFavoriteGameByIdQuery = new GetFavoriteGameByIdQuery(favoriteGameId);
        var favoriteGame = favoriteGameQueryService.handle(getFavoriteGameByIdQuery);
        if (favoriteGame.isEmpty()) return ResponseEntity.notFound().build();
        var favoriteGameEntity = favoriteGame.get();
        var favoriteGameResource = FavoriteGameResourceFromEntityAssembler.toResourceFromEntity(favoriteGameEntity);
        return new ResponseEntity<>(favoriteGameResource, HttpStatus.CREATED);
    }

    /**
     * Get a favorite game by ID
     *
     * @param favoriteGameId The favorite game ID
     * @return The {@link FavoriteGameResource} resource for the favorite game
     */
    @GetMapping("/{favoriteGameId}")
    @Operation(summary = "Get a favorite game by ID", description = "Get a favorite game by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite game found"),
            @ApiResponse(responseCode = "404", description = "Favorite game not found")
    })
    public ResponseEntity<FavoriteGameResource> getCourseById(@PathVariable Long favoriteGameId) {
        var getFavoriteGameByIdQuery= new GetFavoriteGameByIdQuery(favoriteGameId);
        var favoriteGame = favoriteGameQueryService.handle(getFavoriteGameByIdQuery);
        if (favoriteGame.isEmpty()) return ResponseEntity.notFound().build();
        var favoriteGameEntity = favoriteGame.get();
        var favoriteGameResource = FavoriteGameResourceFromEntityAssembler.toResourceFromEntity(favoriteGameEntity);
        return ResponseEntity.ok(favoriteGameResource);
    }

    /**
     * Delete a favorite game by ID
     *
     * @param favoriteGameId The favorite game ID
     * @return The message for the deleted favorite game
     */
    @DeleteMapping("/{favoriteGameId}")
    @Operation(summary = "Delete a favorite game by ID", description = "Delete a favorite game by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Favorite game deleted"),
            @ApiResponse(responseCode = "404", description = "Favorite game not found")
    })
    public ResponseEntity<?> deleteFavoriteGame(@PathVariable Long favoriteGameId) {
        var deleteFavoriteGameCommand = new DeleteFavoriteGameCommand(favoriteGameId);
        favoriteGameCommandService.handle(deleteFavoriteGameCommand);
        return ResponseEntity.ok("Favorite Game with given id successfully deleted");
    }
}