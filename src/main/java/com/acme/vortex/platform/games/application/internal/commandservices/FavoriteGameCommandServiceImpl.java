package com.acme.vortex.platform.games.application.internal.commandservices;

import com.acme.vortex.platform.games.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.games.domain.model.commands.CreateFavoriteGameCommand;
import com.acme.vortex.platform.games.domain.services.FavoriteGameCommandService;
import com.acme.vortex.platform.games.infrastructure.persistence.jpa.FavoriteGameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteGameCommandServiceImpl implements FavoriteGameCommandService {
    private final FavoriteGameRepository favoriteGameRepository;

    public FavoriteGameCommandServiceImpl(FavoriteGameRepository favoriteGameRepository) {
        this.favoriteGameRepository = favoriteGameRepository;
    }

    /**
     * @param command
     * @return The created favorite game.
     */
    @Override
    public Optional<FavoriteGame> handle(CreateFavoriteGameCommand command) {
        if(favoriteGameRepository.existsByNameAndImage(command.name(), command.image()))
            throw new IllegalArgumentException("Favorite game with same name and image already exists");
        var favoriteGame = new FavoriteGame(command);
        var createFavoriteGame = favoriteGameRepository.save(favoriteGame);
        return Optional.of(createFavoriteGame);
    }
}
