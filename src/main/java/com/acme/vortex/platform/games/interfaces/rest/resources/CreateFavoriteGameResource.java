package com.acme.vortex.platform.games.interfaces.rest.resources;

public record CreateFavoriteGameResource(String name, String image) {
    public CreateFavoriteGameResource {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name must not be null or empty");

        if (image == null || image.isBlank())
            throw new IllegalArgumentException("Image must not be null or empty");
    }
}
