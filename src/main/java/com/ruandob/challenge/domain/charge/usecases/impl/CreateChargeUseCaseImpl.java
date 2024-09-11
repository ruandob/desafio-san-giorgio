package com.ruandob.challenge.domain.charge.usecases.impl;

import com.ruandob.challenge.domain.charge.usecases.CreateChargeUseCase;
import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.charge.gateways.CreateChargeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateChargeUseCaseImpl implements CreateChargeUseCase {

    private final CreateChargeGateway createChargeGateway;

    @Override
    public ChargeDomain create(ChargeDomain request) {
        return createChargeGateway.create(request);
    }
}
