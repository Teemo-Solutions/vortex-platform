package com.acme.vortex.platform.games.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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
}