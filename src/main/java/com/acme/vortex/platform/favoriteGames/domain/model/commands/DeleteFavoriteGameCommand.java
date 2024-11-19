package com.acme.vortex.platform.favoriteGames.domain.model.commands;

public record DeleteFavoriteGameCommand(Long favoriteGameId) {
    public DeleteFavoriteGameCommand {
        if (favoriteGameId == null || favoriteGameId <= 0) {
            throw new IllegalArgumentException("favoriteGameId cannot be null or less than 1");
        }
    }
}
