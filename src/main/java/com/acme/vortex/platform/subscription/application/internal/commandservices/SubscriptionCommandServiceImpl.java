package com.acme.vortex.platform.subscription.application.internal.commandservices;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Subscription;
import com.acme.vortex.platform.subscription.domain.models.commands.CreateSubscriptionCommand;
import com.acme.vortex.platform.subscription.domain.services.SubscriptionCommandService;
import com.acme.vortex.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Handles the CreateSubscriptionCommand command.
     *
     * @param command - the CreateSubscriptionCommand command
     * @return an Optional of Subscription
     */

    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        if (subscriptionRepository.existsByUserIdAndPlanId(command.userId(), command.planId())) {
            throw new IllegalArgumentException("Subscription with same plan ID already exists for this user");
        }
        var subscription = new Subscription(command);
        var createdSubscription = subscriptionRepository.save(subscription);
        return Optional.of(createdSubscription);
    }
}
