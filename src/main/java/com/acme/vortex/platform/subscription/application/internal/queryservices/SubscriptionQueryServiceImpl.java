package com.acme.vortex.platform.subscription.application.internal.queryservices;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Subscription;
import com.acme.vortex.platform.subscription.domain.models.queries.GetAllSubscriptionsQuery;
import com.acme.vortex.platform.subscription.domain.models.queries.GetSubscriptionByIdQuery;
import com.acme.vortex.platform.subscription.domain.models.queries.GetSubscriptionByUserIdAndPlanIdQuery;
import com.acme.vortex.platform.subscription.domain.services.SubscriptionQueryService;
import com.acme.vortex.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Subscription;
import com.acme.vortex.platform.subscription.domain.models.queries.GetAllSubscriptionsQuery;
import com.acme.vortex.platform.subscription.domain.models.queries.GetSubscriptionByIdQuery;
import com.acme.vortex.platform.subscription.domain.models.queries.GetSubscriptionByUserIdAndPlanIdQuery;
import com.acme.vortex.platform.subscription.domain.services.SubscriptionQueryService;
import com.acme.vortex.platform.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Handles the GetAllSubscriptionsByUserIdQuery query.
     *
     * @param query - the GetAllSubscriptionsByUserIdQuery query
     * @return a list of Subscription
     */
    @Override
    public List<Subscription> handle(GetAllSubscriptionsQuery query) {
        return subscriptionRepository.findAll();
    }

    /**
     * Handles the GetSubscriptionByIdQuery query.
     *
     * @param query - the GetSubscriptionByIdQuery query
     * @return an Optional of Subscription
     */
    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return subscriptionRepository.findById(query.id());
    }

    /**
     * Handles the GetSubscriptionByUserIdAndPlanIdQuery query.
     *
     * @param query - the GetSubscriptionByUserIdAndPlanIdQuery query
     * @return an Optional of Subscription
     */
    @Override
    public Optional<Subscription> handle(GetSubscriptionByUserIdAndPlanIdQuery query) {
        return subscriptionRepository.findByUserIdAndPlanId(query.userId(), query.planId());
    }
}
