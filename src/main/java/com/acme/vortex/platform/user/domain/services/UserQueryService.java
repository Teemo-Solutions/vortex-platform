package com.acme.vortex.platform.user.domain.services;

import com.acme.vortex.platform.user.domain.model.aggregates.User;
import com.acme.vortex.platform.user.domain.model.queries.GetAllUsersQuery;
import com.acme.vortex.platform.user.domain.model.queries.GetUserByIdQuery;
import com.acme.vortex.platform.user.domain.model.queries.GetUserByEmailQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByEmailQuery query);
}