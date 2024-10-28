package com.acme.vortex.platform.games.interfaces.rest.transform;

import com.acme.vortex.platform.games.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.games.interfaces.rest.resources.FavoriteGameResource;

public class FavoriteGameResourceFromEntityAssembler {
    public static FavoriteGameResource toResourceFromEntity(FavoriteGame entity) {
        return new FavoriteGameResource(entity.getId(), entity.getName(), entity.getImage());
    }
}
