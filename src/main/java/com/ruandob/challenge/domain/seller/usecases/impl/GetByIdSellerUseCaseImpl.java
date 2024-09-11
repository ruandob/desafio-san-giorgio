package com.ruandob.challenge.domain.seller.usecases.impl;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.GetByIdSellerGateway;
import com.ruandob.challenge.domain.seller.usecases.GetByIdSellerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetByIdSellerUseCaseImpl implements GetByIdSellerUseCase {

    private final GetByIdSellerGateway gateway;

    @Override
    public SellerDomain getById(String id) {
        return gateway.getById(id);
    }
}
