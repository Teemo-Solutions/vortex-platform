package com.acme.vortex.platform.games.domain.services;

import com.acme.vortex.platform.games.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.games.domain.model.commands.CreateFavoriteGameCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface FavoriteGameCommandService {
    Optional<FavoriteGame> handle(CreateFavoriteGameCommand command);
}
