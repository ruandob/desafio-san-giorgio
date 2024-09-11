package com.ruandob.challenge.api.payment.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.ruandob.challenge.api.validators.ValidateUUID;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PaymentItemRequest(
        @ValidateUUID
        String chargeId,
        @Min(value = 0, message = "{min_field_value}")
        @NotNull(message = "{notnull_field}")
        BigDecimal value,
        String status
) {
}
