package com.ruandob.challenge.domain.payment;

import java.math.BigDecimal;

public record PaymentItemDomain(
        String chargeId,
        String id,
        BigDecimal value,
        String status
) {

    public PaymentItemDomain addStatus(String status) {
        return new PaymentItemDomain(this.chargeId, this.id, this.value, status);
    }
}
