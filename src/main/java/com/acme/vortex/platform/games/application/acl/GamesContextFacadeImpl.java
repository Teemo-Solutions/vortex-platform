package com.acme.vortex.platform.games.application.acl;

import com.acme.vortex.platform.games.domain.model.commands.CreateGameCommand;
import com.acme.vortex.platform.games.domain.model.queries.GetGameByIdQuery;
import com.acme.vortex.platform.games.domain.services.GameCommandService;
import com.acme.vortex.platform.games.domain.services.GameQueryService;
import com.acme.vortex.platform.games.interfaces.acl.GamesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class GamesContextFacadeImpl implements GamesContextFacade {
    private final GameCommandService gameCommandService;
    private final GameQueryService gameQueryService;

    public GamesContextFacadeImpl(GameCommandService gameCommandService, GameQueryService gameQueryService) {
        this.gameCommandService = gameCommandService;
        this.gameQueryService = gameQueryService;
    }

    /**
     * Create a new game
     *
     * @param title       The title
     * @param description The description
     * @param developer   The developer
     * @param publisher   The publisher
     * @param releaseDate The release date
     * @param genre       The genre
     * @param platforms   The platforms
     * @param rating      The rating
     * @param downloads   The downloads
     * @param imageUrl    The image URL
     * @return The game ID
     */
    @Override
    public Long createGame(String title, String description, String developer, String publisher, String releaseDate, String genre, String platforms, Double rating, Long downloads, String imageUrl) {
        var createGameCommand = new CreateGameCommand(title, description, developer, publisher, releaseDate, genre, platforms, rating, downloads, imageUrl);
        var game = gameCommandService.handle(createGameCommand);
        return game.isEmpty() ? Long.valueOf(0L) : game.get().getId();
    }

    /**
     * Fetch a game ID by title
     *
     * @param id The game ID
     * @return The game ID
     */
    @Override
    public Long fetchGameId(Long id) {
        var getGameByIdQuery = new GetGameByIdQuery(id);
        var game = gameQueryService.handle(getGameByIdQuery);
        return game.isEmpty() ? Long.valueOf(0L) : game.get().getId();
    }
}
