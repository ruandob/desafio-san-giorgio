package com.ruandob.challenge.infrastructure.dataproviders.payment.repository;

import com.ruandob.challenge.infrastructure.dataproviders.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
