package com.acme.vortex.platform.subscription.interfaces.rest;

import com.acme.vortex.platform.subscription.domain.models.aggregates.Subscription;
import com.acme.vortex.platform.subscription.domain.models.queries.GetAllSubscriptionsQuery;
import com.acme.vortex.platform.subscription.domain.models.queries.GetSubscriptionByIdQuery;
import com.acme.vortex.platform.subscription.domain.models.queries.GetSubscriptionByUserIdAndPlanIdQuery;
import com.acme.vortex.platform.subscription.domain.services.SubscriptionCommandService;
import com.acme.vortex.platform.subscription.domain.services.SubscriptionQueryService;
import com.acme.vortex.platform.subscription.interfaces.rest.resources.CreateSubscriptionResource;
import com.acme.vortex.platform.subscription.interfaces.rest.resources.SubscriptionResource;
import com.acme.vortex.platform.subscription.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.acme.vortex.platform.subscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {
    private final SubscriptionCommandService subscriptionCommandService;
    private final SubscriptionQueryService subscriptionQueryService;

    public SubscriptionController(SubscriptionCommandService subscriptionCommandService, SubscriptionQueryService subscriptionQueryService) {
        this.subscriptionCommandService = subscriptionCommandService;
        this.subscriptionQueryService = subscriptionQueryService;
    }

    /**
     * Creates a new subscription.
     *
     * @param resource the resource containing the details of the subscription to be created
     * @return the created subscription resource, or a bad request response if the creation fails
     */
    @PostMapping
    public ResponseEntity<SubscriptionResource> createSubscription(@RequestBody CreateSubscriptionResource resource) {
        Optional<Subscription> subscription = subscriptionCommandService.handle(CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource));
        return subscription.map(sub -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @GetMapping("{id}")
    public ResponseEntity<SubscriptionResource> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionQueryService.handle(new GetSubscriptionByIdQuery(id));
        return subscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    private ResponseEntity<List<SubscriptionResource>> getAllSubscriptionsByUserId() {
        var getAllSubscriptionsByUserIdQuery = new GetAllSubscriptionsQuery();
        var subscriptions = subscriptionQueryService.handle(getAllSubscriptionsByUserIdQuery);
        if (subscriptions.isEmpty()) return ResponseEntity.notFound().build();
        var subscriptionResources = subscriptions.stream().map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(subscriptionResources);
    }

    /**
     * Retrieves a subscription by the specified user ID and plan ID.
     *
     * @param userId the user ID for the subscription
     * @param planId the plan ID of the subscription
     * @return the subscription resource, or a not found response if no such subscription exists
     */
    private ResponseEntity<SubscriptionResource> getSubscriptionByUserIdAndPlanId(String userId, String planId) {
        var getSubscriptionByUserIdAndPlanIdQuery = new GetSubscriptionByUserIdAndPlanIdQuery(userId, planId);
        var subscription = subscriptionQueryService.handle(getSubscriptionByUserIdAndPlanIdQuery);
        if (subscription.isEmpty()) return ResponseEntity.notFound().build();
        return subscription.map(sub -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(sub))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves subscriptions based on the provided query parameters.
     *
     * <p>If both 'userId' and 'planId' are provided, it retrieves the specific subscription.
     * If only 'userId' is provided, it retrieves all subscriptions for that user.
     * If neither is provided, it returns a bad request response.</p>
     *
     * @param params the query parameters
     * @return the subscription resource(s) or a bad request response
     */
    @GetMapping
    public ResponseEntity<?> getSubscriptionsWithParameters(@RequestParam Map<String, String> params) {
        if (params.containsKey("userId") && params.containsKey("planId")) {
            return getSubscriptionByUserIdAndPlanId(params.get("userId"), params.get("planId"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
