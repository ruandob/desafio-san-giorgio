package com.ruandob.challenge.api.controllers.payment.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PaymentItemRequest(
        String chargeId,
        BigDecimal value,
        String status
) {
}
