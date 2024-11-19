package com.acme.vortex.platform.subscription.domain.services;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Payment;
import com.acme.vortex.platform.subscription.domain.models.queries.GetAllPaymentsQuery;
import com.acme.vortex.platform.subscription.domain.models.queries.GetPaymentByIdQuery;


import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    List<Payment> handle (GetAllPaymentsQuery query);
    Optional<Payment> handle (GetPaymentByIdQuery query);


}
