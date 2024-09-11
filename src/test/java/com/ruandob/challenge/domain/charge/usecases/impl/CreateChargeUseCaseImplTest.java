package com.ruandob.challenge.domain.charge.usecases.impl;

import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.charge.gateways.CreateChargeGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateChargeUseCaseImplTest {

    @Mock
    private CreateChargeGateway createChargeGateway;

    @InjectMocks
    private CreateChargeUseCaseImpl createChargeUseCase;

    @Test
    @DisplayName("Deve retornar um ChargeDomain quando uma nova cobrança é criada")
    void create_ShouldReturnChargeDomain_WhenNewChargeIsCreated() {
        ChargeDomain request = new ChargeDomain(null, BigDecimal.TEN, BigDecimal.TEN, emptyList());
        ChargeDomain createdCharge = new ChargeDomain("id", BigDecimal.TEN, BigDecimal.TEN, emptyList());
        when(createChargeGateway.create(request)).thenReturn(createdCharge);

        ChargeDomain result = createChargeUseCase.create(request);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(createdCharge.id());
        assertThat(result.value()).isEqualTo(createdCharge.value());
        assertThat(result.remainingValue()).isEqualTo(createdCharge.remainingValue());

        verify(createChargeGateway, times(1)).create(request);
    }
}
