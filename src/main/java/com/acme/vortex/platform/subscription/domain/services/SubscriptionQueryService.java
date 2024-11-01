package com.acme.vortex.platform.subscription.domain.services;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Subscription;
import com.acme.vortex.platform.subscription.domain.models.queries.*;

import java.util.List;
import java.util.Optional;

public interface SubscriptionQueryService {
    List<Subscription> handle(GetAllSubscriptionsQuery query);
    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
    Optional<Subscription> handle(GetSubscriptionByUserIdAndPlanIdQuery query);
}