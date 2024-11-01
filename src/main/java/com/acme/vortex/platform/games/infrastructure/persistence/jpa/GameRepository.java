package com.acme.vortex.platform.games.infrastructure.persistence.jpa;

import com.acme.vortex.platform.games.domain.model.aggregates.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}