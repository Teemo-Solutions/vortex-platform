package com.acme.vortex.platform.subscription.domain.services;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Payment;
import com.acme.vortex.platform.subscription.domain.models.commands.CreatePaymentCommand;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payment> handle (CreatePaymentCommand command);
}
