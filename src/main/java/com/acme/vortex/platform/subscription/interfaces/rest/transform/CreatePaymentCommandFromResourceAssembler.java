package com.acme.vortex.platform.subscription.interfaces.rest.transform;

import com.acme.vortex.platform.subscription.domain.models.commands.CreatePaymentCommand;
import com.acme.vortex.platform.subscription.interfaces.rest.resources.CreatePaymentResource;

import java.text.SimpleDateFormat;

public class CreatePaymentCommandFromResourceAssembler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        String paymentDate = dateFormat.format(resource.paymentDate());
        return new CreatePaymentCommand(resource.userId(), resource.paymentType(), paymentDate, resource.paymentAmount(), resource.paymentDescription());
    }
}
