package com.ruandob.challenge.infrastructure.dataproviders.charge.providers;

import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.charge.gateways.GetByIdChargeGateway;
import com.ruandob.challenge.domain.exceptions.NotFoundException;
import com.ruandob.challenge.infrastructure.dataproviders.charge.mappers.ChargeDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.charge.repositories.JpaChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetByIdChargeProvider implements GetByIdChargeGateway {

    private final JpaChargeRepository repository;
    private final ChargeDomainMapper mapper;

    @Override
    public ChargeDomain getById(String id) {
        var charge = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("cobrança"));
        return mapper.toDomain(charge);
    }
}
