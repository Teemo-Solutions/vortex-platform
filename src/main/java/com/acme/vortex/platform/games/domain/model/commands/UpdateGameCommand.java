package com.acme.vortex.platform.games.domain.model.commands;

public record UpdateGameCommand( Long gameId, String title, String description, String developer, String publisher, String releaseDate, String genre, String platforms, Double rating, Long downloads, String imageUrl) {
    public UpdateGameCommand {
        if (gameId == null || gameId <= 0) {
            throw new IllegalArgumentException("gameId cannot be null or less than 1");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or blank");
        }
        if (developer == null || developer.isBlank()) {
            throw new IllegalArgumentException("developer cannot be null or blank");
        }
        if (publisher == null || publisher.isBlank()) {
            throw new IllegalArgumentException("publisher cannot be null or blank");
        }
        if (releaseDate == null || releaseDate.isBlank()) {
            throw new IllegalArgumentException("releaseDate cannot be null or blank");
        }
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("genre cannot be null or blank");
        }
        if (platforms == null || platforms.isBlank()) {
            throw new IllegalArgumentException("platforms cannot be null or blank");
        }
        if (rating == null || rating <= 0) {
            throw new IllegalArgumentException("rating cannot be null or less than 1");
        }
        if (downloads == null || downloads <= 0) {
            throw new IllegalArgumentException("downloads cannot be null or less than 1");
        }
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("imageUrl cannot be null or blank");
        }
    }
}
