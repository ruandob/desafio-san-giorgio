package com.ruandob.challenge.domain.charge.usecases;

import com.ruandob.challenge.domain.charge.ChargeDomain;

public interface CreateChargeUseCase {

    ChargeDomain create(ChargeDomain request);
}
