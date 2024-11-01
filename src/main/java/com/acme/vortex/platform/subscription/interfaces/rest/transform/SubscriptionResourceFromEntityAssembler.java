package com.acme.vortex.platform.subscription.interfaces.rest.transform;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Subscription;
import com.acme.vortex.platform.subscription.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {

    /**
     * Converts a Subscription entity to a SubscriptionResource.
     *
     * @param entity the Subscription entity to convert
     * @return the converted SubscriptionResource
     */
    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        return new SubscriptionResource(entity.getId(), entity.getUserId(), entity.getPlanId(), entity.getSubscriptionStartDate(), entity.getSubscriptionEndDate(), entity.getSubscriptionStatus(), entity.getSubscriptionDescription());
    }
}
