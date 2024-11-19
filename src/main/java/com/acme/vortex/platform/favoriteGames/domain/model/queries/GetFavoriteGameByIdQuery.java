package com.acme.vortex.platform.favoriteGames.domain.model.queries;

public record GetFavoriteGameByIdQuery(Long id) {
    public GetFavoriteGameByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
    }
}
