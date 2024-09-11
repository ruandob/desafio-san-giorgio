package com.ruandob.challenge.infrastructure.dataproviders.seller.providers;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.CreateSellerGateway;
import com.ruandob.challenge.infrastructure.dataproviders.seller.mappers.SellerDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.seller.repositories.JpaSellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateSellerProvider implements CreateSellerGateway {

    private final JpaSellerRepository repository;
    private final SellerDomainMapper mapper;

    @Override
    public SellerDomain create(SellerDomain domain) {
        var entity = mapper.toEntity(domain);
        entity = repository.saveAndFlush(entity);
        return mapper.toDomain(entity);
    }
}
