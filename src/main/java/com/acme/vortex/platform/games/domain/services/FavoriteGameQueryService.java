package com.acme.vortex.platform.games.domain.services;

import com.acme.vortex.platform.games.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.games.domain.model.queries.GetAllFavoriteGamesByNameQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetFavoriteGameByIdQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetFavoriteGameByNameAndImageQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FavoriteGameQueryService {
    List<FavoriteGame> handle(GetAllFavoriteGamesByNameQuery query);
    Optional<FavoriteGame> handle(GetFavoriteGameByIdQuery query);
    Optional<FavoriteGame> handle(GetFavoriteGameByNameAndImageQuery query);
}
