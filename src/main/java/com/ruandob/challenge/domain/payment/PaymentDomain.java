package com.ruandob.challenge.domain.payment;

import java.util.List;

public record PaymentDomain(
        String sellerId,
        List<PaymentItemDomain> paymentItens
) {
}
