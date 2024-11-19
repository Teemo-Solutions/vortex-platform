package com.acme.vortex.platform.favoriteGames.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * GameId value object
 * @summary
 * This value object represents the unique identifier of a game.
 * The gameId must be greater than 0. It throws an IllegalArgumentException if the gameId is null or less than or equal to 0.
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public class GameId {

    private Long value;

    protected GameId() {
        // Constructor protegido para JPA
    }

    public GameId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Game ID must be a positive number");
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameId gameId = (GameId) o;
        return value.equals(gameId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
