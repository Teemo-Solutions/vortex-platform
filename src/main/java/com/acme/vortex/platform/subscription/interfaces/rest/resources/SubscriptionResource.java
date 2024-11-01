package com.acme.vortex.platform.subscription.interfaces.rest.resources;

import java.util.Date;

public record SubscriptionResource(Long id, String userId, String planId, Date subscriptionStartDate, Date subscriptionEndDate, String subscriptionStatus, String subscriptionDescription) {
}
