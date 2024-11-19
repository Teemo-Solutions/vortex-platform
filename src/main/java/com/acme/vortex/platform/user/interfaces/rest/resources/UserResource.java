package com.acme.vortex.platform.user.interfaces.rest.resources;

public record UserResource(Long id, String name, String email, String role, String birthDate) {
    public UserResource {
        if (id == null || id < 0)
            throw new IllegalArgumentException("id cannot be null");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or empty");

        if (email == null || email.isBlank())
            throw new IllegalArgumentException("email cannot be null or empty");

        if (role == null || role.isBlank())
            throw new IllegalArgumentException("role cannot be null or empty");

        if (birthDate == null || birthDate.isBlank())
            throw new IllegalArgumentException("birthDate cannot be null");

    }
}