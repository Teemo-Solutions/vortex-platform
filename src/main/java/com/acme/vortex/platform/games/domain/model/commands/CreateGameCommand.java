package com.acme.vortex.platform.games.domain.model.commands;

public record CreateGameCommand(
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