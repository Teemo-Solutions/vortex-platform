package com.acme.vortex.platform.subscription.infrastructure.persistence.jpa.repositories;

import com.acme.vortex.platform.profile.domain.model.valueobjects.ProfileId;
import com.acme.vortex.platform.subscription.domain.models.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

   Optional<Payment>findById(Long id);
}