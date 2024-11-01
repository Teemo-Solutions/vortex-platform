package com.acme.vortex.platform.user.interfaces.rest.transform;

import com.acme.vortex.platform.user.domain.model.aggregates.User;
import com.acme.vortex.platform.user.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(), entity.getEmail(), entity.getName(), entity.getRole(), entity.getBirthDate());
    }
}