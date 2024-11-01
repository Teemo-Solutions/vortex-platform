package com.acme.vortex.platform.games.domain.model.queries;

public record GetFavoriteGameByNameAndImageQuery(String name, String image) {
    public GetFavoriteGameByNameAndImageQuery {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or empty");

        if (image == null || image.isBlank())
            throw new IllegalArgumentException("image cannot be null or empty");
    }
}
