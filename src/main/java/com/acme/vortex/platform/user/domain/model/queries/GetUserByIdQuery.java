package com.acme.vortex.platform.user.domain.model.queries;

public record GetUserByIdQuery(Long id) {
    public GetUserByIdQuery {
        if (id == null || id < 0)
            throw new IllegalArgumentException("id cannot be null");
    }
}
