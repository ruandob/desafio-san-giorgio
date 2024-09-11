package com.ruandob.challenge.domain.seller.usecases.impl;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.CreateSellerGateway;
import com.ruandob.challenge.domain.seller.usecases.CreateSellerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateSellerUseCaseImpl implements CreateSellerUseCase {

    private final CreateSellerGateway gateway;

    @Override
    public SellerDomain create(SellerDomain domain) {
        return gateway.create(domain);
    }
}
