package com.acme.vortex.platform.games.interfaces.rest;

import com.acme.vortex.platform.games.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.games.domain.services.FavoriteGameCommandService;
import com.acme.vortex.platform.games.interfaces.rest.resources.CreateFavoriteGameResource;
import com.acme.vortex.platform.games.interfaces.rest.resources.FavoriteGameResource;
import com.acme.vortex.platform.games.interfaces.rest.transform.CreateFavoriteGameCommandFromResourceAssembler;
import com.acme.vortex.platform.games.interfaces.rest.transform.FavoriteGameResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/favorite-games", produces = APPLICATION_JSON_VALUE)
@Tag(name="Favorite Games", description = "Operation related to favorite games")
public class FavoriteGameController {
    private final FavoriteGameCommandService favoriteGameCommandService;

    public FavoriteGameController(FavoriteGameCommandService favoriteGameCommandService) {
        this.favoriteGameCommandService = favoriteGameCommandService;
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
}