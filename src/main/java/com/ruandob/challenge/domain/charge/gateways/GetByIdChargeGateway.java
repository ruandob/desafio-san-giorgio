package com.ruandob.challenge.domain.charge.gateways;

import com.ruandob.challenge.domain.charge.ChargeDomain;

public interface GetByIdChargeGateway {


    ChargeDomain getById(String id);
}
