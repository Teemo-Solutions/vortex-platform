package com.acme.vortex.platform.user.infrastructure.persistence.jpa;

import com.acme.vortex.platform.user.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

public interface UserRepository  extends JpaRepository<User, Long>{

    List<User> findAllByBirthDat
}
