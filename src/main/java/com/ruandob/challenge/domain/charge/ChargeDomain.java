package com.ruandob.challenge.domain.charge;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ChargeDomain(
        String id,
        BigDecimal value,
        BigDecimal remainingValue,
        List<PaymentItemDomain> payments
) {
}
