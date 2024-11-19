package com.acme.vortex.platform.favoriteGames.interfaces.rest.transform;

import com.acme.vortex.platform.favoriteGames.domain.model.aggregates.FavoriteGame;
import com.acme.vortex.platform.favoriteGames.interfaces.rest.resources.FavoriteGameResource;

/**
 * Assembler to convert a FavoriteGame entity to a FavoriteGameResource.
 */
public class FavoriteGameResourceFromEntityAssembler {
    public  static FavoriteGameResource toResourceFromEntity(FavoriteGame entity) {
        return new FavoriteGameResource(entity.getId(), entity.getUserIdAsLong(), entity.getGameIdAsLong());
    }
}
