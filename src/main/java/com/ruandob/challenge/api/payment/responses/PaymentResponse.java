package com.ruandob.challenge.api.payment.responses;

import java.math.BigDecimal;

public record PaymentResponse(
        String id,
        BigDecimal value,
        String status
) {
}
