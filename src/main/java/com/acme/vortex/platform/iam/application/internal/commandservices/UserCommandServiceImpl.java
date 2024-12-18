package com.acme.vortex.platform.iam.application.internal.commandservices;

import com.acme.vortex.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.acme.vortex.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.acme.vortex.platform.iam.domain.model.aggregates.User;
import com.acme.vortex.platform.iam.domain.model.commands.SignInCommand;
import com.acme.vortex.platform.iam.domain.model.commands.SignUpCommand;
import com.acme.vortex.platform.iam.domain.services.UserCommandService;
import com.acme.vortex.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.acme.vortex.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    public UserCommandServiceImpl(UserRepository userRepository, RoleRepository roleRepository, HashingService hashingService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return the created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles().stream().map(
                role -> roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Roles name not found"))
        ).toList();
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}
