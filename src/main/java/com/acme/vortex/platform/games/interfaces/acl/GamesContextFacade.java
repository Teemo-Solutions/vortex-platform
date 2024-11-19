package com.acme.vortex.platform.games.interfaces.acl;

/**
 * GamesContextFacade
 */
public interface GamesContextFacade {

    /**
     * Create a new game
     * @param title The title
     * @param description The description
     * @param developer The developer
     * @param publisher The publisher
     * @param releaseDate The release date
     * @param genre The genre
     * @param platforms The platforms
     * @param rating The rating
     * @param downloads The downloads
     * @param imageUrl The image URL
     * @return The game ID
     */
    Long createGame(
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
    );

    /**
     * Fetch a game ID by title
     * @return The game ID
     */
    Long fetchGameId(Long id);
}
