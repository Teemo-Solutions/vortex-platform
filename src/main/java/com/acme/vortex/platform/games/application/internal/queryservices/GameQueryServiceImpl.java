package com.acme.vortex.platform.games.application.internal.queryservices;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import com.acme.vortex.platform.games.domain.model.queries.GetAllGamesByTitleQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetAllGamesQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetGameByIdQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetGameByTitleAndDeveloperQuery;
import com.acme.vortex.platform.games.domain.services.GameQueryService;
import com.acme.vortex.platform.games.infrastructure.persistence.jpa.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameQueryServiceImpl implements GameQueryService {

    private final GameRepository gameRepository;

    public GameQueryServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> handle(GetAllGamesByTitleQuery query) {
        return gameRepository.findByTitleContainingIgnoreCase(query.title());
    }

    @Override
    public Optional<Game> handle(GetGameByIdQuery query) {
        return gameRepository.findById(query.id());
    }

    @Override
    public Optional<Game> handle(GetGameByTitleAndDeveloperQuery query) {
        return gameRepository.findByTitleAndDeveloper(query.title(), query.developer());
    }

    @Override
    public List<Game> handle(GetAllGamesQuery query) {
        return gameRepository.findAll();
    }
}