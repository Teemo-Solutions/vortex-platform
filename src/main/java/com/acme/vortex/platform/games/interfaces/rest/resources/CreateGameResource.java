package com.acme.vortex.platform.games.interfaces.rest.resources;

public record CreateGameResource(
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
) {}