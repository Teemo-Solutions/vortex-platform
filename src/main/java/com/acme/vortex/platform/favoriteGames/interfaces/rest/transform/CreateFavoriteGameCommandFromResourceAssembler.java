package com.acme.vortex.platform.favoriteGames.interfaces.rest.transform;

import com.acme.vortex.platform.favoriteGames.domain.model.commands.CreateFavoriteGameCommand;
import com.acme.vortex.platform.favoriteGames.interfaces.rest.resources.CreateFavoriteGameResource;

/**
 * Assembler to convert a CreateFavoriteGameResource to a CreateFavoriteGameCommand.
 */
public class CreateFavoriteGameCommandFromResourceAssembler {

    /**
     * Converts a CreateFavoriteGameResource to a CreateFavoriteGameCommand.
     *
     * @param resource The {@link CreateFavoriteGameResource} resource to convert.
     * @return The {@link CreateFavoriteGameCommand} command that results from the conversion.
     */
    public static CreateFavoriteGameCommand toCommandFromResource(CreateFavoriteGameResource resource) {
        return new CreateFavoriteGameCommand(resource.userId(), resource.gameId());
    }
}
