package com.acme.vortex.platform.profile.interfaces.rest.controller;

import com.acme.vortex.platform.profile.domain.model.Profile;
import com.acme.vortex.platform.profile.infrastructure.persistence.jpa.entity.ProfileEntity;
import com.acme.vortex.platform.profile.infrastructure.persistence.jpa.repository.ProfileRepository;
import com.acme.vortex.platform.profile.interfaces.rest.transform.CreateProfileResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Profiles", description = "Profile management endpoints")
@RestController
@RequestMapping("/api/v1/profiles")
public class ProfilesController {

    private final ProfileRepository profileRepository;

    public ProfilesController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Operation(summary = "Create a new profile")
    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody CreateProfileResource resource) {
        // Validar si ya existe el username o email
        if (profileRepository.existsByUsername(resource.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (profileRepository.existsByEmail(resource.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        // Crear nueva entidad
        ProfileEntity entity = new ProfileEntity();
        entity.setUsername(resource.getUsername());
        entity.setEmail(resource.getEmail());
        entity.setBiography(resource.getBiography());
        entity.setAvatarUrl(resource.getAvatarUrl());

        // Guardar y retornar
        ProfileEntity savedEntity = profileRepository.save(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all profiles")
    @GetMapping
    public ResponseEntity<List<ProfileEntity>> getAllProfiles() {
        List<ProfileEntity> profiles = profileRepository.findAll();
        return ResponseEntity.ok(profiles);
    }
}