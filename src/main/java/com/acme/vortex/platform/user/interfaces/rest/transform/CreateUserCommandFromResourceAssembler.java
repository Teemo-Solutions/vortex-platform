package com.acme.vortex.platform.user.interfaces.rest.transform;

import com.acme.vortex.platform.user.domain.model.commands.CreateUserCommand;
import com.acme.vortex.platform.user.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public  static CreateUserCommand toCommand(CreateUserResource resource){
        return new CreateUserCommand(resource.email(), resource.name(), resource.password(), resource.role(), resource.birthDate());
    }
}
