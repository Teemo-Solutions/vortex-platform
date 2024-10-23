package com.acme.vortex.platform.games;

public record GetFavoriteGameByNameAndIdQuery(String name, Long id) {
    public GetFavoriteGameByNameAndIdQuery {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name cannot be null or empty");

        if (id == null || id <= 0)
            throw new IllegalArgumentException("id cannot be null or empty");
    }
}
