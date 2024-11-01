package com.acme.vortex.platform.user.interfaces.rest;

import com.acme.vortex.platform.user.domain.model.aggregates.User;
import com.acme.vortex.platform.user.domain.model.queries.GetAllUsersQuery;
import com.acme.vortex.platform.user.domain.model.queries.GetUserByIdQuery;
import com.acme.vortex.platform.user.domain.model.queries.GetUserByEmailQuery;
import com.acme.vortex.platform.user.domain.services.UserCommandService;
import com.acme.vortex.platform.user.domain.services.UserQueryService;
import com.acme.vortex.platform.user.interfaces.rest.resources.CreateUserResource;
import com.acme.vortex.platform.user.interfaces.rest.resources.UserResource;
import com.acme.vortex.platform.user.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.acme.vortex.platform.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name="Users", description = "Operation related to users")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @Operation(
            summary = "Register a user",
            description = "Create a user with the provided name, email, password, role, and birth date"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        if (resource.birthDate() == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<User> user = userCommandService
                .handle(CreateUserCommandFromResourceAssembler.toCommand(resource));
        return user.map(u -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(u), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get all users",
            description = "Retrieve a list of all users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        List<User> users = userQueryService.handle(new GetAllUsersQuery());
        List<UserResource> userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(userResources, OK);
    }

    @Operation(
            summary = "Get user by ID",
            description = "Retrieve a user by their ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        Optional<User> user = userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(u -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(u), OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get user by email",
            description = "Retrieve a user by their email"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    @GetMapping("/search")
    public ResponseEntity<UserResource> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userQueryService.handle(new GetUserByEmailQuery(email));
        return user.map(u -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(u), OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}