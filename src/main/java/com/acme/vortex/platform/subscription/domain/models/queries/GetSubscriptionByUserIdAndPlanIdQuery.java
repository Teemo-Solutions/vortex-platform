package com.acme.vortex.platform.subscription.domain.models.queries;


public record GetSubscriptionByUserIdAndPlanIdQuery(String userId, String planId) {
    public GetSubscriptionByUserIdAndPlanIdQuery {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId cannot be null or empty");
        }
        if (planId == null || planId.isBlank()) {
            throw new IllegalArgumentException("planId cannot be null or empty");
        }
    }
}
