package com.acme.vortex.platform.profile.infrastructure.persistence.jpa.repository;

import com.acme.vortex.platform.profile.infrastructure.persistence.jpa.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}