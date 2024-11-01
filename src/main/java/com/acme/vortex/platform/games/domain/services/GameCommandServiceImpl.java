package com.acme.vortex.platform.games.domain.services;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import com.acme.vortex.platform.games.domain.model.commands.CreateGameCommand;
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
}