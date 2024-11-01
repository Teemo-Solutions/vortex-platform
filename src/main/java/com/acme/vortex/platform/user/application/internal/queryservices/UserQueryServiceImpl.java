package com.acme.vortex.platform.user.application.internal.queryservices;

import com.acme.vortex.platform.user.domain.model.aggregates.User;
import com.acme.vortex.platform.user.domain.model.queries.GetUserByIdQuery;
import com.acme.vortex.platform.user.domain.model.queries.GetUserByEmailQuery;
import com.acme.vortex.platform.user.domain.model.queries.GetAllUsersQuery;
import com.acme.vortex.platform.user.domain.services.UserQueryService;
import com.acme.vortex.platform.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param query
     * @return
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAllUsers();
    }

    /**
     * @param query
     * @return
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }

    /**
     * @param query
     * @return
     */
    @Override
    public Optional<User> handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.email());
    }
}

