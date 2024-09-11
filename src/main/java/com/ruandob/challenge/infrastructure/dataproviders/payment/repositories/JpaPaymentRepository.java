package com.ruandob.challenge.infrastructure.dataproviders.payment.repositories;

import com.ruandob.challenge.infrastructure.dataproviders.payment.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
