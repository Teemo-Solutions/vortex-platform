package com.acme.vortex.platform.iam.domain.model.queries;

import com.acme.vortex.platform.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
