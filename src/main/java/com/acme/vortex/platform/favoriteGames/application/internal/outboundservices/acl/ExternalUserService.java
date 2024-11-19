package com.acme.vortex.platform.favoriteGames.application.internal.outboundservices.acl;

import com.acme.vortex.platform.favoriteGames.domain.model.valueobjects.UserId;
import com.acme.vortex.platform.user.interfaces.acl.UsersContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * External User Service
 */
@Service
public class ExternalUserService {
    private final UsersContextFacade usersContextFacade;

    /**
     * Constructor
     *
     * @param usersContextFacade User Context Facade
     */
    public ExternalUserService(UsersContextFacade usersContextFacade) {
        this.usersContextFacade = usersContextFacade;
    }

    /**
     * Fetch User By Email
     * @param email
     * @return An {@link Optional} of {@link UserId}
     */
    public Optional<UserId> fetchUserByEmail(String email) {
        var userId = usersContextFacade.fetchUserIdByEmail(email);
        return userId == 0L ? Optional.empty() : Optional.of(new UserId(userId));
    }

    public Optional<UserId> createUser(String name, String email, String password, String role, String birthDate) {
        var userId = usersContextFacade.createUser(name, email, password, role, birthDate);
        return userId == 0L ? Optional.empty() : Optional.of(new UserId(userId));
    }
}


