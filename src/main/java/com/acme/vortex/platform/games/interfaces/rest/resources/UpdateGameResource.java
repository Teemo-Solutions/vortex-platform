package com.acme.vortex.platform.games.interfaces.rest.resources;


public record UpdateGameResource(String title, String description, String developer, String publisher, String releaseDate, String genre, String platforms, Double rating, Long downloads, String imageUrl) {
    public UpdateGameResource {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (developer == null || developer.isBlank()) {
            throw new IllegalArgumentException("Developer is required");
        }
        if (publisher == null || publisher.isBlank()) {
            throw new IllegalArgumentException("Publisher is required");
        }
        if (releaseDate == null || releaseDate.isBlank()) {
            throw new IllegalArgumentException("Release date is required");
        }
        if (genre == null || genre.isBlank()) {
            throw new IllegalArgumentException("Genre is required");
        }
        if (platforms == null || platforms.isBlank()) {
            throw new IllegalArgumentException("Platforms is required");
        }
        if (rating == null) {
            throw new IllegalArgumentException("Rating is required");
        }
        if (downloads == null) {
            throw new IllegalArgumentException("Downloads is required");
        }
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("Image URL is required");
        }
    }
}
