package com.acme.vortex.platform.games.domain.services;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import com.acme.vortex.platform.games.domain.model.queries.GetAllGamesByTitleQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetAllGamesQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetGameByIdQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetGameByTitleAndDeveloperQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GameQueryService {
    List<Game> handle(GetAllGamesByTitleQuery query);
    Optional<Game> handle(GetGameByIdQuery query);
    Optional<Game> handle(GetGameByTitleAndDeveloperQuery query);
    List<Game> handle(GetAllGamesQuery query);

}