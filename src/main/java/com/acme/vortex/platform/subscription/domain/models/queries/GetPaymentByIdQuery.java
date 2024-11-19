package com.acme.vortex.platform.subscription.domain.models.queries;

public record GetPaymentByIdQuery(Long id) {
    public GetPaymentByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
