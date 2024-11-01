package com.acme.vortex.platform.games.infrastructure.persistence.jpa;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByTitleContainingIgnoreCase(String title);
    Optional<Game> findByTitleAndDeveloper(String title, String developer);
}