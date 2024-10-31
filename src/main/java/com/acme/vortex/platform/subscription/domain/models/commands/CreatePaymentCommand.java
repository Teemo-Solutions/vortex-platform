package com.acme.vortex.platform.subscription.domain.models.commands;

import java.util.Date;

public record CreatePaymentCommand(String userId, String paymentType, String paymentDate, String paymentAmount, String paymentDescription) {

    public CreatePaymentCommand {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId cannot be null or empty");
        }
        if (paymentType == null || paymentType.isBlank()) {
            throw new IllegalArgumentException("PaymentType cannot be null or empty");
        }
        if (paymentDate == null || paymentDate.isBlank()) {
            throw new IllegalArgumentException("PaymentDate cannot be null or empty");
        }
        if (paymentAmount == null || paymentAmount.isBlank()) {
            throw new IllegalArgumentException("PaymentAmount cannot be null or empty");
        }
        if (paymentDescription == null || paymentDescription.isBlank()) {
            throw new IllegalArgumentException("PaymentDescription cannot be null or empty");
        }
    }
}
