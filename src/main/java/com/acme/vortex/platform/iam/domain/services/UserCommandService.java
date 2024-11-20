package com.acme.vortex.platform.iam.domain.services;

import com.acme.vortex.platform.iam.domain.model.aggregates.User;
import com.acme.vortex.platform.iam.domain.model.commands.SignInCommand;
import com.acme.vortex.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);

    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

}
