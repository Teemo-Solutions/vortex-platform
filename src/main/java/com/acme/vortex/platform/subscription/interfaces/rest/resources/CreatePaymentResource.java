package com.acme.vortex.platform.subscription.interfaces.rest.resources;

import java.util.Date;

public record CreatePaymentResource(String userId, String paymentType, Date paymentDate, String paymentAmount, String paymentDescription) {

    public CreatePaymentResource {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId cannot be null or empty");
        }
        if (paymentType == null || paymentType.isBlank()) {
            throw new IllegalArgumentException("paymentType cannot be null or empty");
        }
        if (paymentDate == null ) {
            throw new IllegalArgumentException("paymentDate cannot be null or empty");
        }
        if (paymentAmount == null || paymentAmount.isBlank()) {
            throw new IllegalArgumentException("paymentAmount cannot be null or empty");
        }
        if (paymentDescription == null || paymentDescription.isBlank()) {
            throw new IllegalArgumentException("paymentDescription cannot be null or empty");
        }
    }

}
