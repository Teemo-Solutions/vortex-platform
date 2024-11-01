package com.acme.vortex.platform.games.domain.services;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import com.acme.vortex.platform.games.domain.model.commands.CreateGameCommand;

import java.util.Optional;

public interface GameCommandService {
    Optional<Game> handle(CreateGameCommand command);
}