package com.acme.vortex.platform.favoriteGames.application.internal.queryservices;

import com.acme.vortex.platform.favoriteGames.domain.exceptions.FavoriteGameNotFound;
import com.acme.vortex.platform.favoriteGames.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.favoriteGames.domain.model.queries.GetFavoriteGameByIdQuery;
import com.acme.vortex.platform.favoriteGames.domain.model.queries.GetFavoriteGameByUserIdAndGameIdQuery;
import com.acme.vortex.platform.favoriteGames.domain.model.valueobjects.GameId;
import com.acme.vortex.platform.favoriteGames.domain.model.valueobjects.UserId;
import com.acme.vortex.platform.favoriteGames.domain.services.FavoriteGameQueryService;
import com.acme.vortex.platform.favoriteGames.infrastructure.persistence.jpa.repositories.FavoriteGameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FavoriteGameQueryServiceImpl implements FavoriteGameQueryService {

    private final FavoriteGameRepository favoriteGameRepository;

    /**
     * Constructor
     *
     * @param favoriteGameRepository The favorite game repository
     */
    public FavoriteGameQueryServiceImpl(FavoriteGameRepository favoriteGameRepository) {
        this.favoriteGameRepository = favoriteGameRepository;
    }

    /**
     * Handle a get favorite game by id query
     *
     * @param query The get favorite game by id query containing the favorite game id
     * @return The favorite game
     * @see GetFavoriteGameByIdQuery
     */
    @Override
    public Optional<FavoriteGame> handle(GetFavoriteGameByIdQuery query) {
        return favoriteGameRepository.findById(query.id());
    }

    /**
     * Handle a get favorite game by user id and game id query
     *
     * @param query The get favorite game by user id and game id query containing the user id and game id
     * @return The favorite game
     * @see GetFavoriteGameByUserIdAndGameIdQuery
     */
    @Override
    public Optional<FavoriteGame> handle(GetFavoriteGameByUserIdAndGameIdQuery query) {
        // Convert Long to value objects
        GameId gameId = new GameId(query.gameId());
        UserId userId = new UserId(query.userId());

        // Check if the game exists
        if (!favoriteGameRepository.existsByGameId(gameId)) {
            throw new FavoriteGameNotFound(query.gameId());
        }

        // Fetch the favorite game by userId and gameId
        return favoriteGameRepository.findByGameId(gameId)
                .filter(favoriteGame -> {
                    favoriteGame.getUserIdAsLong();
                    return false;
                });
    }
}
