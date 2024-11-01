package com.acme.vortex.platform.user.domain.model.commands;

public record CreateUserCommand(String name, String email, String password, String role, String birthDate) {
    public CreateUserCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or empty");

        if (email == null || email.isBlank())
            throw new IllegalArgumentException("email cannot be null or empty");

        if (password == null || password.isBlank())
            throw new IllegalArgumentException("password cannot be null or empty");

        if (role == null || role.isBlank())
            throw new IllegalArgumentException("role cannot be null or empty");

        if (birthDate == null)
            throw new IllegalArgumentException("birthDate cannot be null");

    }
}