package com.acme.vortex.platform.games.application.internal.queryservices;

import com.acme.vortex.platform.games.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.games.domain.model.queries.GetAllFavoriteGamesByNameQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetFavoriteGameByIdQuery;
import com.acme.vortex.platform.games.domain.model.queries.GetFavoriteGameByNameAndImageQuery;
import com.acme.vortex.platform.games.domain.services.FavoriteGameQueryService;
import com.acme.vortex.platform.games.infrastructure.persistence.jpa.FavoriteGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteGameQueryServiceImpl implements FavoriteGameQueryService {
    private final FavoriteGameRepository favoriteGameRepository;

    public FavoriteGameQueryServiceImpl(FavoriteGameRepository favoriteGameRepository) {
        this.favoriteGameRepository = favoriteGameRepository;
    }

    /**
     * @param query
     * @return
     */
    @Override
    public List<FavoriteGame> handle(GetAllFavoriteGamesByNameQuery query) {
        return favoriteGameRepository.findAllByName(query.name());
    }

    /**
     * @param query
     * @return
     */
    @Override
    public Optional<FavoriteGame> handle(GetFavoriteGameByIdQuery query) {
        return favoriteGameRepository.findById(query.id());
    }

    /**
     * @param query
     * @return
     */
    @Override
    public Optional<FavoriteGame> handle(GetFavoriteGameByNameAndImageQuery query) {
        return favoriteGameRepository.findByNameAndImage(query.name(), query.image());
    }
}