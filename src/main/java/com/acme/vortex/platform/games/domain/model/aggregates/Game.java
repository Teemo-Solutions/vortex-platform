package com.acme.vortex.platform.games.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String developer;
    private String publisher;
    private String releaseDate;
    private String genre;
    private String platforms;
    private Double rating;
    private Long downloads;
    private String imageUrl;

    public Game() {
    }

    public Game(String title, String description, String developer, String publisher, String releaseDate,
                String genre, String platforms, Double rating, Long downloads, String imageUrl) {
        this.title = title;
        this.description = description;
        this.developer = developer;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.platforms = platforms;
        this.rating = rating;
        this.downloads = downloads;
        this.imageUrl = imageUrl;
    }

    /**
     * Update the information of the game
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
     * @return The updated game
     */
    public Game updateInformation(String title, String description, String developer, String publisher, String releaseDate,
                                  String genre, String platforms, Double rating, Long downloads, String imageUrl) {
        this.title = title;
        this.description = description;
        this.developer = developer;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.platforms = platforms;
        this.rating = rating;
        this.downloads = downloads;
        this.imageUrl = imageUrl;
        return this;
    }

}