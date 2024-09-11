package com.ruandob.challenge.api.charge.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ruandob.challenge.api.payment.responses.PaymentResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChargeResponse(
        String id,
        BigDecimal value,
        BigDecimal remainingValue,
        List<PaymentResponse> payments
) {
}
