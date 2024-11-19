package com.acme.vortex.platform.favoriteGames.domain.exceptions;

/**
 * Exception thrown when a favorite game is not found
 */
public class FavoriteGameNotFound extends RuntimeException {
    /**
     * Constructor for the exception.
     * @param favoriteGameId The ID of the favorite game that was not found.
     */
    public FavoriteGameNotFound(Long favoriteGameId) {
        super(String.format("Favorite game with ID %s not found.", favoriteGameId));
    }
}
