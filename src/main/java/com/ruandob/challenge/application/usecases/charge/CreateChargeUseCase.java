package com.ruandob.challenge.application.usecases.charge;

import com.ruandob.challenge.api.controllers.charge.requests.ChargeRequest;
import com.ruandob.challenge.api.controllers.charge.responses.ChargeResponse;

public interface CreateChargeUseCase {

    ChargeResponse create(ChargeRequest request);
}
