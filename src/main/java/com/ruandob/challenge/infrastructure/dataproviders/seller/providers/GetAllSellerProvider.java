package com.ruandob.challenge.infrastructure.dataproviders.seller.providers;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.GetAllSellerGateway;
import com.ruandob.challenge.infrastructure.dataproviders.seller.mappers.SellerDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.seller.repositories.JpaSellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllSellerProvider implements GetAllSellerGateway {

    private final JpaSellerRepository repository;
    private final SellerDomainMapper mapper;

    @Override
    public List<SellerDomain> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
