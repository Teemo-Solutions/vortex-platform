package com.acme.vortex.platform.user.application.acl;

import com.acme.vortex.platform.user.domain.model.commands.CreateUserCommand;
import com.acme.vortex.platform.user.domain.model.queries.GetUserByEmailQuery;
import com.acme.vortex.platform.user.domain.services.UserCommandService;
import com.acme.vortex.platform.user.domain.services.UserQueryService;
import com.acme.vortex.platform.user.interfaces.acl.UsersContextFacade;
import org.springframework.stereotype.Service;

@Service
public class UsersContextFacadeImpl implements UsersContextFacade {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UsersContextFacadeImpl(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Create a new user
     *
     * @param name      The name
     * @param email     The email address
     * @param password  The password
     * @param role      The role
     * @param birthDate The birth date
     * @return The user ID
     */
    @Override
    public Long createUser(String name, String email, String password, String role, String birthDate) {
        var createUserCommand = new CreateUserCommand(name, email, password, role, birthDate);
        var user = userCommandService.handle(createUserCommand);
        return user.isEmpty() ? Long.valueOf(0L) : user.get().getId();
    }

    /**
     * Fetch a user ID by email
     *
     * @param email The email address
     * @return The user ID
     */
    @Override
    public Long fetchUserIdByEmail(String email) {
        var getUserByEmailQuery = new GetUserByEmailQuery(email);
        var user = userQueryService.handle(getUserByEmailQuery);
        return user.isEmpty() ? Long.valueOf(0L) : user.get().getId();
    }
}
