package com.acme.vortex.platform.games.domain.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(Long gameId) {
        super(String.format("Game with ID %s not found.", gameId));
    }
}
