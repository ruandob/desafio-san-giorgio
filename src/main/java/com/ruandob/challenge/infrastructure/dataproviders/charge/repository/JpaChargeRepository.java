package com.ruandob.challenge.infrastructure.dataproviders.charge.repository;

import com.ruandob.challenge.infrastructure.dataproviders.charge.entity.ChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaChargeRepository extends JpaRepository<ChargeEntity, UUID> {
}
