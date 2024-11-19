package com.acme.vortex.platform.favoriteGames.interfaces.rest.resources;

/**
 * FavoriteGameResource is a record class that represents a favorite game resource.
 *
 */
public record FavoriteGameResource(Long id, Long userId, Long gameId) {
}
