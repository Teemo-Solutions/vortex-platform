package com.acme.vortex.platform.user.domain.model.aggregates;

import com.acme.vortex.platform.user.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * User Aggregate Root
 *
 * @summary
 * This class represents a FavoriteGame entity, which is a game that a user has marked as favorite.
 * It is responsable for handling the CreateFavoriteGameCommand
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter
    private String password;

    @Column(nullable = false)
    @Getter
    private String birthDate;

    @Column(nullable = false)
    @Getter
    private String role;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    protected User() {}

    /**
     * @summary Constructor for FavoriteGame
     * It creates a new FavoriteGame instance based on the CreateFavoriteGameCommand command.
     * @param command - The CreateFavoriteGameCommand command
     */

    public User(CreateUserCommand command){
        this.name = command.name();
        this.email = command.email();
        this.password = command.password();
        this.birthDate = command.birthDate();
        this.role = command.role();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}