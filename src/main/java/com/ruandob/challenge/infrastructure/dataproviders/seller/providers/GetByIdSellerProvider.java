package com.ruandob.challenge.infrastructure.dataproviders.seller.providers;

import com.ruandob.challenge.domain.exceptions.NotFoundException;
import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.GetByIdSellerGateway;
import com.ruandob.challenge.infrastructure.dataproviders.seller.mappers.SellerDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.seller.repositories.JpaSellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetByIdSellerProvider implements GetByIdSellerGateway {

    private final JpaSellerRepository repository;
    private final SellerDomainMapper mapper;

    @Override
    public SellerDomain getById(String id) {
        var entity = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("vendedor", id));
        return mapper.toDomain(entity);
    }
}
