package com.acme.vortex.platform.games.infrastructure.persistence.jpa;

import com.acme.vortex.platform.games.domain.model.aggregates.FavoriteGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteGameRepository extends JpaRepository<FavoriteGame, Long> {

    List<FavoriteGame> findAllByName(String name);

    boolean existsByNameAndImage(String name, String image);

    Optional<FavoriteGame> findByNameAndImage(String name, String image);
}
