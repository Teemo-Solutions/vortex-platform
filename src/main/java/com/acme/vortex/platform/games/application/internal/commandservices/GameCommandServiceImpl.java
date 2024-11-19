package com.acme.vortex.platform.games.application.internal.commandservices;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import com.acme.vortex.platform.games.domain.model.commands.CreateGameCommand;
import com.acme.vortex.platform.games.domain.model.commands.DeleteGameCommand;
import com.acme.vortex.platform.games.domain.model.commands.UpdateGameCommand;
import com.acme.vortex.platform.games.domain.services.GameCommandService;
import com.acme.vortex.platform.games.infrastructure.persistence.jpa.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameCommandServiceImpl implements GameCommandService {

    private final GameRepository gameRepository;

    public GameCommandServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Optional<Game> handle(CreateGameCommand command) {
        Game game = new Game(
                command.title(),
                command.description(),
                command.developer(),
                command.publisher(),
                command.releaseDate(),
                command.genre(),
                command.platforms(),
                command.rating(),
                command.downloads(),
                command.imageUrl()
        );
        return Optional.of(gameRepository.save(game));
    }

    @Override
    public Optional<Game> handle(UpdateGameCommand command) {
        if(gameRepository.existsByTitleAndIdIsNot(command.title(), command.gameId()))
            throw new IllegalArgumentException("Game with title %s already exists".formatted(command.title()));
        var result = gameRepository.findById(command.gameId());
        if(result.isEmpty())
            throw new IllegalArgumentException("Game with id %s not found".formatted(command.gameId()));
        var gameToUpdate = result.get();
        try {
            var updatedGame = gameRepository.save(gameToUpdate.updateInformation(
                    command.title(),
                    command.description(),
                    command.developer(),
                    command.publisher(),
                    command.releaseDate(),
                    command.genre(),
                    command.platforms(),
                    command.rating(),
                    command.downloads(),
                    command.imageUrl()
            ));
            return Optional.of(updatedGame);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating game: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteGameCommand command) {
        if(!gameRepository.existsById(command.gameId()))
            throw new IllegalArgumentException("Game with id %s not found".formatted(command.gameId()));
        try {
            gameRepository.deleteById(command.gameId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting game: %s".formatted(e.getMessage()));
        }
    }
}