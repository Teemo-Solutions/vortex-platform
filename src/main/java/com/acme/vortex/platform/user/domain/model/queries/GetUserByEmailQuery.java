package com.acme.vortex.platform.user.domain.model.queries;

public record GetUserByEmailQuery(String email) {
    public GetUserByEmailQuery {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("email cannot be null or empty");
    }
}