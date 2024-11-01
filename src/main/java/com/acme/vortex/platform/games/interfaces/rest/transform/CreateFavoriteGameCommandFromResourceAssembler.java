package com.acme.vortex.platform.games.interfaces.rest.transform;

import com.acme.vortex.platform.games.domain.model.commands.CreateFavoriteGameCommand;
import com.acme.vortex.platform.games.interfaces.rest.resources.CreateFavoriteGameResource;

public class CreateFavoriteGameCommandFromResourceAssembler {
    public  static CreateFavoriteGameCommand toCommand(CreateFavoriteGameResource resource){
        return new CreateFavoriteGameCommand(resource.name(), resource.image()); 
    }
}
