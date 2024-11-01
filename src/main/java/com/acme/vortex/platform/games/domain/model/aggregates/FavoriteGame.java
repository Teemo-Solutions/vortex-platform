package com.acme.vortex.platform.games.domain.model.aggregates;

import com.acme.vortex.platform.games.domain.model.commands.CreateFavoriteGameCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * FavoriteGame Aggregate Root
 *
 * @summary
 * This class represents a FavoriteGame entity, which is a game that a user has marked as favorite.
 * It is responsable for handling the CreateFavoriteGameCommand
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class FavoriteGame extends AbstractAggregateRoot<FavoriteGame> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private String image;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    protected FavoriteGame() {}

    /**
     * @summary Constructor for FavoriteGame
     * It creates a new FavoriteGame instance based on the CreateFavoriteGameCommand command.
     * @param command - The CreateFavoriteGameCommand command
     */

    public FavoriteGame(CreateFavoriteGameCommand command){
        this.name = command.name();
        this.image = command.image();
    }
}