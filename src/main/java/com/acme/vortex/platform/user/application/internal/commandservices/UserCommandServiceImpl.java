package com.acme.vortex.platform.user.application.internal.commandservices;

import com.acme.vortex.platform.user.domain.model.aggregates.User;
import com.acme.vortex.platform.user.domain.model.commands.CreateUserCommand;
import com.acme.vortex.platform.user.domain.services.UserCommandService;
import com.acme.vortex.platform.user.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param command
     * @return The created favorite game.
     */
    @Override
    public Optional<User> handle(CreateUserCommand command) {
        if(userRepository.existsByEmail(command.email()))
            throw new IllegalArgumentException("Favorite game with same name and image already exists");
        var user = new User(command);
        var createUser = userRepository.save(user);
        return Optional.of(createUser);
    }
}
