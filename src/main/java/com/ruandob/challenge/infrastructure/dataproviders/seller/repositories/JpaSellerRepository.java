package com.ruandob.challenge.infrastructure.dataproviders.seller.repositories;

import com.ruandob.challenge.infrastructure.dataproviders.seller.entities.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaSellerRepository extends JpaRepository<SellerEntity, UUID> {
}
