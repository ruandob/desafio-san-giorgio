package com.ruandob.challenge.api.payment.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ruandob.challenge.api.validators.ValidateUUID;
import jakarta.validation.Valid;
import lombok.Builder;

import java.util.List;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PaymentRequest(
        @ValidateUUID
        String sellerId,
        @Valid
        List<PaymentItemRequest> paymentItens
) {
}
