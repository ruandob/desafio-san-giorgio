package com.ruandob.challenge.domain.charge.usecases.impl;

import com.ruandob.challenge.domain.charge.usecases.GetByIdChargeUseCase;
import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.charge.gateways.GetByIdChargeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetByIdChargeUseCaseImpl implements GetByIdChargeUseCase {

    private final GetByIdChargeGateway gateway;

    @Override
    public ChargeDomain getById(String id) {
        return gateway.getById(id);
    }
}
