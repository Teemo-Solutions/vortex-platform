package com.acme.vortex.platform.favoriteGames.application.internal.commandservices;

import com.acme.vortex.platform.favoriteGames.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.favoriteGames.domain.model.commands.CreateFavoriteGameCommand;
import com.acme.vortex.platform.favoriteGames.domain.model.commands.DeleteFavoriteGameCommand;
import com.acme.vortex.platform.favoriteGames.domain.model.valueobjects.GameId;
import com.acme.vortex.platform.favoriteGames.domain.services.FavoriteGameCommandService;
import com.acme.vortex.platform.favoriteGames.infrastructure.persistence.jpa.repositories.FavoriteGameRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteGameCommandServiceImpl implements FavoriteGameCommandService {

    private final FavoriteGameRepository favoriteGameRepository;

    public FavoriteGameCommandServiceImpl(FavoriteGameRepository favoriteGameRepository) {
        this.favoriteGameRepository = favoriteGameRepository;
    }

    /**
     * Handles the creation of a favorite game.
     *
     * @param command the command containing data for the favorite game to create.
     * @return the ID of the created favorite game.
     */
    @Override
    public Long handle(CreateFavoriteGameCommand command) {
        // Convert gameId from Long to GameId
        GameId gameId = new GameId(command.gameId());

        // Check if the game already exists
        if (favoriteGameRepository.existsByGameId(gameId)) {
            throw new IllegalArgumentException("Favorite game with id %s already exists".formatted(command.gameId()));
        }

        // Create and save the favorite game
        var favoriteGame = new FavoriteGame(command);
        try {
            favoriteGameRepository.save(favoriteGame);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving favorite game: %s".formatted(e.getMessage()));
        }

        return favoriteGame.getId();
    }

    /**
     * Handles the deletion of a favorite game.
     *
     * @param command the command containing data for the favorite game to delete.
     */
    @Override
    public void handle(DeleteFavoriteGameCommand command) {
        // Convert gameId from Long to GameId
        GameId gameId = new GameId(command.favoriteGameId());

        // Check if the game exists
        if (!favoriteGameRepository.existsByGameId(gameId)) {
            throw new IllegalArgumentException("Favorite game with id %s not found".formatted(command.favoriteGameId()));
        }

        // Delete the favorite game
        try {
            favoriteGameRepository.deleteById(command.favoriteGameId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deleting favorite game: %s".formatted(e.getMessage()));
        }
    }
}
