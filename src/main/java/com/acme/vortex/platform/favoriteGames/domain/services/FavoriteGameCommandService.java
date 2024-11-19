package com.acme.vortex.platform.favoriteGames.domain.services;

import com.acme.vortex.platform.favoriteGames.domain.model.commands.CreateFavoriteGameCommand;
import com.acme.vortex.platform.favoriteGames.domain.model.commands.DeleteFavoriteGameCommand;

public interface FavoriteGameCommandService {
    Long handle(CreateFavoriteGameCommand command);

    void handle(DeleteFavoriteGameCommand command);
}
