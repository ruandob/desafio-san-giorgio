package com.ruandob.challenge.domain.charge.usecases.impl;

import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.charge.gateways.GetByIdChargeGateway;
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
class GetByIdChargeUseCaseImplTest {

    @Mock
    private GetByIdChargeGateway gateway;

    @InjectMocks
    private GetByIdChargeUseCaseImpl getByIdChargeUseCase;

    @Test
    @DisplayName("Deve retornar um ChargeDomain quando uma cobrança é buscada por ID")
    void getById_ShouldReturnChargeDomain_WhenChargeExists() {
        String id = "some-id";
        ChargeDomain chargeDomain = new ChargeDomain(id, BigDecimal.TEN, BigDecimal.TEN, emptyList());
        when(gateway.getById(id)).thenReturn(chargeDomain);

        ChargeDomain result = getByIdChargeUseCase.getById(id);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(id);
        assertThat(result.value()).isEqualTo(BigDecimal.TEN);
        assertThat(result.remainingValue()).isEqualTo(BigDecimal.TEN);

        verify(gateway, times(1)).getById(id);
    }
}
