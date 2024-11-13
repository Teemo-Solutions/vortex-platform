package com.acme.vortex.platform.user.domain.services;

import com.acme.vortex.platform.user.domain.model.aggregates.User;
import com.acme.vortex.platform.user.domain.model.commands.CreateUserCommand;
import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
}