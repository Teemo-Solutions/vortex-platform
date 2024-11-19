package com.acme.vortex.platform.games.domain.model.commands;

public record DeleteGameCommand(Long gameId) {

    /**
     * Constructor
     * @param gameId the game id.
     *               Cannot be null or less than 1
     * @throws IllegalArgumentException if gameId is null or less than 1
     */
    public DeleteGameCommand {
        if (gameId == null || gameId <= 0) {
            throw new IllegalArgumentException("gameId cannot be null or less than 1");
        }
    }
}
