package com.acme.vortex.platform.user.infrastructure.persistence.jpa;

import com.acme.vortex.platform.user.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}