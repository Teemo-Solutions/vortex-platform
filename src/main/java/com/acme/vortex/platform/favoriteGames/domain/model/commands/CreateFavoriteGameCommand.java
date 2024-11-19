package com.acme.vortex.platform.favoriteGames.domain.model.commands;

public record CreateFavoriteGameCommand(Long userId, Long gameId) {
    public CreateFavoriteGameCommand {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("userId cannot be null or less than 1");
        }
        if (gameId == null || gameId <= 0) {
            throw new IllegalArgumentException("gameId cannot be null or less than 1");
        }
    }
}
