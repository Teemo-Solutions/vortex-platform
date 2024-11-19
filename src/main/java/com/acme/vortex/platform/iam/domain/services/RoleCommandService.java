package com.acme.vortex.platform.iam.domain.services;

import com.acme.vortex.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
