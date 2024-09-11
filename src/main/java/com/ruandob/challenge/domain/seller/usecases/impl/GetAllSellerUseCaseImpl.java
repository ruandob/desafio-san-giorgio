package com.ruandob.challenge.domain.seller.usecases.impl;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.GetAllSellerGateway;
import com.ruandob.challenge.domain.seller.usecases.GetAllSellerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllSellerUseCaseImpl implements GetAllSellerUseCase {

    private final GetAllSellerGateway gateway;

    @Override
    public List<SellerDomain> getAll() {
        return gateway.getAll();
    }
}
