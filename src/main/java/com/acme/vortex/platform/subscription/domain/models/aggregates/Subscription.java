package com.acme.vortex.platform.subscription.domain.models.aggregates;

import com.acme.vortex.platform.subscription.domain.models.commands.CreateSubscriptionCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class) // it is used to automatically populate the createdAt and updatedAt fields
public class Subscription extends AbstractAggregateRoot<Subscription> {
    /**
     * The unique identifier of the subscription.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    /**
     * The identifier of the user.
     */
    @Column(nullable = false)
    @Getter
    private String userId;

    /**
     * The identifier of the subscription plan.
     */
    @Column(nullable = false)
    @Getter
    private String planId;

    /**
     * The date and time when the subscription was created.
     */
    @Getter
    @Column(nullable = false, updatable = false)
    private Date subscriptionStartDate;

    /**
     * The date and time when the subscription was last updated.
     */
    @Getter
    @Column(nullable = false, updatable = false)
    private Date subscriptionEndDate;

    @Getter
    @Column(nullable = false)
    private String SubscriptionStatus;

    @Getter
    @Column(nullable = false)
    private String SubscriptionDescription;

    /**
     * Protected no-arg constructor for JPA.
     */
    protected Subscription() {
    }

    /**
     * Constructor.
     * It creates a new Subscription instance.
     *
     * @param command the CreateSubscriptionCommand command containing the details of the subscription
     */
    public Subscription(CreateSubscriptionCommand command) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.userId = command.userId();
            this.planId = command.planId();
            this.subscriptionStartDate = format.parse(command.subscriptionStartDate());
            this.subscriptionEndDate = format.parse(command.subscriptionEndDate());
            this.SubscriptionStatus = command.SubscriptionStatus();
            this.SubscriptionDescription = command.SubscriptionDescription();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
