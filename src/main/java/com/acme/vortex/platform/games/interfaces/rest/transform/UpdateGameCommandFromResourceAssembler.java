package com.acme.vortex.platform.games.interfaces.rest.transform;

import com.acme.vortex.platform.games.domain.model.commands.UpdateGameCommand;
import com.acme.vortex.platform.games.interfaces.rest.resources.UpdateGameResource;

public class UpdateGameCommandFromResourceAssembler {
    public static UpdateGameCommand ToCommandFromResource(Long gameId, UpdateGameResource resource) {
        return new UpdateGameCommand(
                gameId,
                resource.title(),
                resource.description(),
                resource.developer(),
                resource.publisher(),
                resource.releaseDate(),
                resource.genre(),
                resource.platforms(),
                resource.rating(),
                resource.downloads(),
                resource.imageUrl()
        );
    }
}