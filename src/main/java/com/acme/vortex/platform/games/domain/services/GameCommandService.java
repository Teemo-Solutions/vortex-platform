package com.acme.vortex.platform.games.domain.services;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import com.acme.vortex.platform.games.domain.model.commands.CreateGameCommand;
import com.acme.vortex.platform.games.domain.model.commands.DeleteGameCommand;
import com.acme.vortex.platform.games.domain.model.commands.UpdateGameCommand;

import java.util.Optional;

public interface GameCommandService {
    Optional<Game> handle(CreateGameCommand command);
    Optional<Game> handle(UpdateGameCommand command);
    void handle(DeleteGameCommand command);
}