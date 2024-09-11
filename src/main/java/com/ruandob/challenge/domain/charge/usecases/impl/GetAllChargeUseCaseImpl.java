package com.ruandob.challenge.domain.charge.usecases.impl;

import com.ruandob.challenge.domain.charge.usecases.GetAllChargeUseCase;
import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.charge.gateways.GetAllChargeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllChargeUseCaseImpl implements GetAllChargeUseCase {
    private final GetAllChargeGateway gateway;

    @Override
    public List<ChargeDomain> getAll() {
        return gateway.getAll();
    }
}
