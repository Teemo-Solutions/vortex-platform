package com.acme.vortex.platform.favoriteGames.interfaces.rest.resources;

public record CreateFavoriteGameResource(Long userId, Long gameId) {
    public CreateFavoriteGameResource {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("userId cannot be null or less than 1");
        }
        if (gameId == null || gameId <= 0) {
            throw new IllegalArgumentException("gameId cannot be null or less than 1");
        }
    }
}