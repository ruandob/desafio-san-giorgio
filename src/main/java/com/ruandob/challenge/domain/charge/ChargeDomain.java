package com.ruandob.challenge.domain.charge;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ChargeDomain(
        String id,
        BigDecimal value,
        BigDecimal remainingValue
) {
}
