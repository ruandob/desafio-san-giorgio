package com.ruandob.challenge.api.charge.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ChargeRequest(

        @Min(value = 0, message = "{min_field_value}")
        @NotNull(message = "{notnull_field}")
        BigDecimal value
) {
}
