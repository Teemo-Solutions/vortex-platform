package com.acme.vortex.platform.favoriteGames.domain.services;

import com.acme.vortex.platform.favoriteGames.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.favoriteGames.domain.model.queries.GetFavoriteGameByIdQuery;
import com.acme.vortex.platform.favoriteGames.domain.model.queries.GetFavoriteGameByUserIdAndGameIdQuery;

import java.util.Optional;

public interface FavoriteGameQueryService {

    /**
     * Handle a get favorite game by id query
     * @param query The get favorite game by id query containing the favorite game id
     * @return The favorite game
     * @see GetFavoriteGameByIdQuery
     */
    Optional<FavoriteGame> handle(GetFavoriteGameByIdQuery query);

    /**
     * Handle a get favorite game by user id and game id query
     * @param query The get favorite game by user id and game id query containing the user id and game id
     * @return The favorite game
     * @see GetFavoriteGameByUserIdAndGameIdQuery
     */
    Optional<FavoriteGame> handle(GetFavoriteGameByUserIdAndGameIdQuery query);
}
