package com.acme.vortex.platform.user.interfaces.rest;

import com.acme.vortex.platform.user.domain.model.aggregates.User;
import com.acme.vortex.platform.user.domain.services.UserCommandService;
import com.acme.vortex.platform.user.interfaces.rest.resources.CreateUserResource;
import com.acme.vortex.platform.user.interfaces.rest.resources.UserResource;
import com.acme.vortex.platform.user.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.acme.vortex.platform.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name="Users", description = "Operation related to Users")
public class UserController {
    private final UserCommandService userCommandService;

    public UserController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @Operation(
            summary = "Create a favorite game",
            description = "Create a favorite game with the provided name and image"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<UserResource> createFavoriteSource(@RequestBody CreateUserResource resource) {
        Optional<User> favoriteSource = userCommandService
                .handle(CreateUserCommandFromResourceAssembler.toCommand(resource));
        return favoriteSource.map(source -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}