package com.ruandob.challenge.infrastructure.dataproviders.charge.repositories;

import com.ruandob.challenge.infrastructure.dataproviders.charge.entities.ChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaChargeRepository extends JpaRepository<ChargeEntity, UUID> {
}
