package com.acme.vortex.platform.games;

public record GetFavoriteGameByIdQuery(Long id) {
    public GetFavoriteGameByIdQuery {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("id cannot be null or empty");
    }
}