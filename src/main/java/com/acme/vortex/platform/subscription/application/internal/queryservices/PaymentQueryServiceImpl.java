package com.acme.vortex.platform.subscription.application.internal.queryservices;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Payment;
import com.acme.vortex.platform.subscription.domain.models.queries.GetPaymentByIdQuery;
import com.acme.vortex.platform.subscription.domain.models.queries.GetAllPaymentsQuery;
import com.acme.vortex.platform.subscription.domain.services.PaymentQueryService;
import com.acme.vortex.platform.subscription.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Handles the GetAllPaymentsQuery query.
     *
     * @param query - the GetAllPaymentsQuery query
     * @return a list of Payment
     */
    @Override
    public List<Payment> handle(GetAllPaymentsQuery query) {
        return paymentRepository.findAll();
    }

    /**
     * Handles the GetPaymentByIdQuery query.
     *
     * @param query - the GetPaymentByIdQuery query
     * @return an Optional of Payment
     */
    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query){
        return paymentRepository.findById(query.id());
    }
}