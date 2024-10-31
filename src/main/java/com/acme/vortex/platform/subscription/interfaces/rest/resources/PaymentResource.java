package com.acme.vortex.platform.subscription.interfaces.rest.resources;

import java.util.Date;

public record PaymentResource(Long id, String userId, String paymentType, Date paymentDate, String paymentAmount, String paymentDescription) {
}
