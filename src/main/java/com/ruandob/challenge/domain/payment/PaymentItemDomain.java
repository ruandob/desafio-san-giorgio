package com.ruandob.challenge.domain.payment;

import java.math.BigDecimal;

public record PaymentItemDomain(
        String sellerId,
        String chargeId,
        String id,
        BigDecimal value,
        String status
) {

    public PaymentItemDomain addSellerId(String sellerId) {
        return new PaymentItemDomain(sellerId, this.chargeId, this.id, this.value, this.status);
    }

    public PaymentItemDomain addStatus(String status) {
        return new PaymentItemDomain(this.sellerId, this.chargeId, this.id, this.value, status);
    }
}
