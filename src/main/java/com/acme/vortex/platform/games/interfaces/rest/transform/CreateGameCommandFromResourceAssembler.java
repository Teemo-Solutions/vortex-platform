package com.acme.vortex.platform.games.interfaces.rest.transform;

import com.acme.vortex.platform.games.domain.model.commands.CreateGameCommand;
import com.acme.vortex.platform.games.interfaces.rest.resources.CreateGameResource;

public class CreateGameCommandFromResourceAssembler {
    public static CreateGameCommand toCommand(CreateGameResource resource) {
        return new CreateGameCommand(
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