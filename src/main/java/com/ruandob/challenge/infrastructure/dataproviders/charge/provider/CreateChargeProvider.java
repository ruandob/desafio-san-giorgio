package com.ruandob.challenge.infrastructure.dataproviders.charge.provider;

import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.charge.gateway.CreateChargeGateway;
import com.ruandob.challenge.infrastructure.dataproviders.charge.mapper.ChargeDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.charge.repository.JpaChargeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateChargeProvider implements CreateChargeGateway {

    private final JpaChargeRepository repository;
    private final ChargeDomainMapper mapper;

    @Override
    public ChargeDomain create(ChargeDomain domain) {
        var entity = mapper.toEntity(domain);
        entity = repository.saveAndFlush(entity);
        log.info("[CreateChargeProvider] Charge create with success: {}", entity);
        return mapper.toDomain(entity);
    }
}
