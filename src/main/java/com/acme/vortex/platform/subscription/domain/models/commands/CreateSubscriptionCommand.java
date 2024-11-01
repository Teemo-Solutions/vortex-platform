package com.acme.vortex.platform.subscription.domain.models.commands;

public record CreateSubscriptionCommand(String userId, String planId, String subscriptionStartDate, String subscriptionEndDate, String SubscriptionStatus, String SubscriptionDescription) {
    public CreateSubscriptionCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId cannot be null or empty");
        }
        if (planId == null || planId.isBlank()) {
            throw new IllegalArgumentException("planId cannot be null or empty");
        }
        if (subscriptionStartDate == null || subscriptionStartDate.isBlank()) {
            throw new IllegalArgumentException("subscriptionStartDate cannot be null or empty");
        }
        if (subscriptionEndDate == null || subscriptionEndDate.isBlank()) {
            throw new IllegalArgumentException("subscriptionEndDate cannot be null or empty");
        }
        if (SubscriptionStatus == null || SubscriptionStatus.isBlank()) {
            throw new IllegalArgumentException("SubscriptionStatus cannot be null or empty");
        }
        if (SubscriptionDescription == null || SubscriptionDescription.isBlank()) {
            throw new IllegalArgumentException("SubscriptionDescription cannot be null or empty");
        }
    }
}
