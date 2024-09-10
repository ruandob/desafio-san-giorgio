package com.ruandob.challenge.application.usecases.charge.impl;

import com.ruandob.challenge.api.controllers.charge.requests.ChargeRequest;
import com.ruandob.challenge.api.controllers.charge.responses.ChargeResponse;
import com.ruandob.challenge.application.usecases.charge.CreateChargeUseCase;
import com.ruandob.challenge.application.usecases.charge.mappers.ChargeRequestMapper;
import com.ruandob.challenge.application.usecases.charge.mappers.ChargeResponseMapper;
import com.ruandob.challenge.domain.charge.gateway.CreateChargeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateChargeUseCaseImpl implements CreateChargeUseCase {

    private final CreateChargeGateway createChargeGateway;
    private final ChargeRequestMapper requestMapper;
    private final ChargeResponseMapper responseMapper;

    @Override
    public ChargeResponse create(ChargeRequest request) {
        var domain = requestMapper.toDomain(request);
        domain = createChargeGateway.create(domain);
        return responseMapper.toResponse(domain);
    }
}
