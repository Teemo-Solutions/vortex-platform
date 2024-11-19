package com.acme.vortex.platform.favoriteGames.infrastructure.persistence.jpa.repositories;

import com.acme.vortex.platform.favoriteGames.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.favoriteGames.domain.model.valueobjects.GameId;
import com.acme.vortex.platform.favoriteGames.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavoriteGameRepository extends JpaRepository<FavoriteGame, Long> {

    Optional<FavoriteGame> findByGameId(GameId gameId);

    Optional<FavoriteGame> findByUserId(UserId userId);

    boolean existsByGameId(GameId gameId);
}

