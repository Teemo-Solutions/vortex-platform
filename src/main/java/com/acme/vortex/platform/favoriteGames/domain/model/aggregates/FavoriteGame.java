package com.acme.vortex.platform.favoriteGames.domain.model.aggregates;

import com.acme.vortex.platform.favoriteGames.domain.model.commands.CreateFavoriteGameCommand;
import com.acme.vortex.platform.favoriteGames.domain.model.valueobjects.GameId;
import com.acme.vortex.platform.favoriteGames.domain.model.valueobjects.UserId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class FavoriteGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "game_id"))
    private GameId gameId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    @Column(nullable = true, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = true)
    @LastModifiedDate
    private Date updatedAt;

    protected FavoriteGame() {}

    public FavoriteGame(CreateFavoriteGameCommand command) {
        this.gameId = new GameId(command.gameId());
        this.userId = new UserId(command.userId());

    }

    public Long getGameIdAsLong() {
        return gameId.getValue();
    }

    public Long getUserIdAsLong() {
        return userId.getValue();
    }
}
