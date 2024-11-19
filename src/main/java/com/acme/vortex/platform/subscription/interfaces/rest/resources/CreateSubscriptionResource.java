package com.acme.vortex.platform.subscription.interfaces.rest.resources;

import java.util.Date;

public record CreateSubscriptionResource(String userId, String planId, Date subscriptionStartDate, Date subscriptionEndDate, String subscriptionStatus, String subscriptionDescription) {
    public CreateSubscriptionResource {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        if (planId == null) {
            throw new IllegalArgumentException("planId cannot be null");
        }
        if (subscriptionStartDate == null) {
            throw new IllegalArgumentException("subscriptionStartDate cannot be null");
        }
        if (subscriptionEndDate == null) {
            throw new IllegalArgumentException("subscriptionEndDate cannot be null");
        }
        if (subscriptionStatus == null) {
            throw new IllegalArgumentException("SubscriptionStatus cannot be null");
        }
        if (subscriptionDescription == null) {
            throw new IllegalArgumentException("SubscriptionDescription cannot be null");
        }
    }
}
