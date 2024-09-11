package com.ruandob.challenge.domain.charge.gateways;

import com.ruandob.challenge.domain.charge.ChargeDomain;

import java.util.List;

public interface GetAllChargeGateway {
    List<ChargeDomain> getAll();
}
