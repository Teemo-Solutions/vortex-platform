package com.acme.vortex.platform.subscription.application.internal.commandservices;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Payment;
import com.acme.vortex.platform.subscription.domain.models.commands.CreatePaymentCommand;
import com.acme.vortex.platform.subscription.domain.services.PaymentCommandService;
import com.acme.vortex.platform.subscription.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        var payment = new Payment(command);
        var createdPayment = paymentRepository.save(payment);
        return Optional.of(createdPayment);
    }
}
