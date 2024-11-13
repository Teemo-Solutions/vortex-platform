package com.acme.vortex.platform.games.interfaces.rest.transform;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import com.acme.vortex.platform.games.interfaces.rest.resources.GameResource;

public class GameResourceFromEntityAssembler {
    public static GameResource toResourceFromEntity(Game entity) {
        return new GameResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDeveloper(),
                entity.getPublisher(),
                entity.getReleaseDate(),
                entity.getGenre(),
                entity.getPlatforms(),
                entity.getRating(),
                entity.getDownloads(),
                entity.getImageUrl()
        );
    }
}