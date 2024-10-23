package com.acme.vortex.platform.games;

public record CreateFavoriteGameCommand(String name, String image) {
    public CreateFavoriteGameCommand {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or empty");

        if (image == null || image.isBlank())
            throw new IllegalArgumentException("image cannot be null or empty");
    }
}
