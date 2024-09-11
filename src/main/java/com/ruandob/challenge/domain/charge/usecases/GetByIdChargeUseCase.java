package com.ruandob.challenge.domain.charge.usecases;

import com.ruandob.challenge.domain.charge.ChargeDomain;

public interface GetByIdChargeUseCase {

    ChargeDomain getById(String id);
}
