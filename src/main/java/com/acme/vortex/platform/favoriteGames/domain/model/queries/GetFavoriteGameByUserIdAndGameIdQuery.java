package com.acme.vortex.platform.favoriteGames.domain.model.queries;

public record GetFavoriteGameByUserIdAndGameIdQuery(Long userId, Long gameId) {
    public GetFavoriteGameByUserIdAndGameIdQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("userId cannot be null or less than 1");
        }
        if (gameId == null || gameId <= 0) {
            throw new IllegalArgumentException("gameId cannot be null or less than 1");
        }
    }
}
