package com.ruandob.challenge.domain.charge.usecases.impl;

import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.charge.gateways.GetAllChargeGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllChargeUseCaseImplTest {

    @Mock
    private GetAllChargeGateway gateway;

    @InjectMocks
    private GetAllChargeUseCaseImpl getAllChargeUseCase;

    @Test
    @DisplayName("Deve retornar uma lista de ChargeDomain quando todas as cobranças são buscadas")
    void getAll_ShouldReturnListOfChargeDomain_WhenChargesExist() {
        var charge1 = new ChargeDomain("id1", BigDecimal.TEN, BigDecimal.TEN, emptyList());
        var charge2 = new ChargeDomain("id2", BigDecimal.valueOf(20), BigDecimal.valueOf(20), emptyList());
        var charges = List.of(charge1, charge2);

        when(gateway.getAll()).thenReturn(charges);

        List<ChargeDomain> result = getAllChargeUseCase.getAll();

        assertThat(result)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(charge1, charge2);

        verify(gateway, times(1)).getAll();
    }
}
