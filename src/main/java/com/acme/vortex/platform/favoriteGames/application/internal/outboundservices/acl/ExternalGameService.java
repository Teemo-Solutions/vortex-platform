package com.acme.vortex.platform.favoriteGames.application.internal.outboundservices.acl;

import com.acme.vortex.platform.favoriteGames.domain.model.valueobjects.GameId;
import com.acme.vortex.platform.games.interfaces.acl.GamesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalGameService {
    private final GamesContextFacade gamesContextFacade;

    /**
     * Constructor
     *
     * @param gamesContextFacade Games Context Facade
     */
    public ExternalGameService(GamesContextFacade gamesContextFacade) {
        this.gamesContextFacade = gamesContextFacade;
    }

    /**
     * Fetch a GameId by its ID using the GamesContextFacade
     *
     * @param id The ID of the game to fetch
     * @return An Optional containing the GameId, or empty if not found
     */
    public Optional<GameId> fetchGameById(Long id) {
        var gameId = gamesContextFacade.fetchGameId(id);
        return gameId == 0L ? Optional.empty() : Optional.of(new GameId(gameId));
    }

    /**
     * Create a game using the GamesContextFacade
     *
     * @param title The title of the game
     * @param description The description of the game
     * @param developer The developer of the game
     * @param publisher The publisher of the game
     * @param releaseDate The release date of the game
     * @param genre The genre of the game
     * @param platforms The platforms the game is available on
     * @param rating The rating of the game
     * @param downloads The number of downloads of the game
     * @param imageUrl The URL of the image of the game
     * @return An Optional containing the GameId of the created game, or empty if creation failed
     */
    public Optional<GameId> createGame (
            String title,
            String description,
            String developer,
            String publisher,
            String releaseDate,
            String genre,
            String platforms,
            Double rating,
            Long downloads,
            String imageUrl
    ) {
        var gameId = gamesContextFacade.createGame(title, description, developer, publisher, releaseDate, genre, platforms, rating, downloads, imageUrl);
        return gameId == 0L ? Optional.empty() : Optional.of(new GameId(gameId));
    }
}
