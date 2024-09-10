package com.ruandob.challenge.api.controllers.payment.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.util.List;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PaymentRequest(
        String clientId,
        List<PaymentItemRequest> paymentItens
) {
}
