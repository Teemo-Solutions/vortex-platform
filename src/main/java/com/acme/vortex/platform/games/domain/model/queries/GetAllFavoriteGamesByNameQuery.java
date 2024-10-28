package com.acme.vortex.platform.games.domain.model.queries;

public record GetAllFavoriteGamesByNameQuery(String name) {
    public GetAllFavoriteGamesByNameQuery {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or empty");
    }
}
