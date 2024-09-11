package com.ruandob.challenge.domain.charge.usecases;

import com.ruandob.challenge.domain.charge.ChargeDomain;

import java.util.List;

public interface GetAllChargeUseCase {
    List<ChargeDomain> getAll();
}
