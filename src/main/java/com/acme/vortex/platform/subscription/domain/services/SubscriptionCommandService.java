package com.acme.vortex.platform.subscription.domain.services;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Subscription;
import com.acme.vortex.platform.subscription.domain.models.commands.CreateSubscriptionCommand;

import java.util.Optional;

public interface SubscriptionCommandService {
    Optional<Subscription> handle(CreateSubscriptionCommand command);
}
