package com.acme.vortex.platform.games.domain.model.queries;

public record GetFavoriteGameByIdQuery(Long id) {
    public GetFavoriteGameByIdQuery {
        if (id == null || id < 0)
            throw new IllegalArgumentException("ID must be null or negative");
    }
}