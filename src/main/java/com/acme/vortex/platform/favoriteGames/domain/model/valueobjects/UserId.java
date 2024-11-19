package com.acme.vortex.platform.favoriteGames.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Value object representing the user id.
 * @summary
 * This value object is used to represent the user id. It is an embeddable object that is used to represent the user id in the favorite game entity.
 * It throws an IllegalArgumentException if the user id is null or less than or equal to 0.
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public class UserId {

    private Long value;

    protected UserId() {
        // Constructor protegido para JPA
    }

    public UserId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("User ID must be a positive number");
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
        UserId userId = (UserId) o;
        return value.equals(userId.value);
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

