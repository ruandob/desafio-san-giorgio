package com.ruandob.challenge.domain.payment.usecases;

import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.charge.gateways.GetByIdChargeGateway;
import com.ruandob.challenge.domain.exceptions.NotFoundException;
import com.ruandob.challenge.domain.payment.PaymentDomain;
import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.domain.payment.strategies.impl.ConfirmFullPaymentStrategyImpl;
import com.ruandob.challenge.domain.payment.strategies.impl.ConfirmOverPaymentStrategyImpl;
import com.ruandob.challenge.domain.payment.strategies.impl.ConfirmPartialPaymentStrategyImpl;
import com.ruandob.challenge.domain.payment.usecases.impl.ConfirmPaymentUseCaseImpl;
import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.GetByIdSellerGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmPaymentUseCaseImplTest {

    @Mock
    private GetByIdSellerGateway getByIdSellerGateway;

    @Mock
    private GetByIdChargeGateway getByIdChargeGateway;

    @Mock
    private ConfirmPartialPaymentStrategyImpl partialPaymentStrategy;

    @Mock
    private ConfirmFullPaymentStrategyImpl fullPaymentStrategy;

    @Mock
    private ConfirmOverPaymentStrategyImpl overPaymentStrategy;

    @InjectMocks
    private ConfirmPaymentUseCaseImpl confirmPaymentUseCase;

    @Test
    @DisplayName("Deve processar pagamentos e aplicar estratégia correspondente quando condição for atendida")
    void execute_ShouldApplyStrategy_WhenConditionIsMet() {
        var sellerId = UUID.randomUUID().toString();
        var chargeId = UUID.randomUUID().toString();
        var paymentItem = new PaymentItemDomain(UUID.randomUUID().toString(), chargeId, null, BigDecimal.ONE, null);
        var paymentDomain = new PaymentDomain(sellerId, List.of(paymentItem));
        var seller = new SellerDomain(sellerId, "Vendedor");
        var charge = new ChargeDomain(chargeId, BigDecimal.TEN, BigDecimal.TEN, emptyList());

        when(getByIdSellerGateway.getById(sellerId)).thenReturn(seller);
        when(getByIdChargeGateway.getById(chargeId)).thenReturn(charge);
        when(partialPaymentStrategy.condition(any(), any())).thenReturn(true);
        doNothing().when(partialPaymentStrategy).execute(any());

        confirmPaymentUseCase.execute(paymentDomain);

        verify(partialPaymentStrategy, times(1)).execute(any());
        verify(fullPaymentStrategy, never()).execute(any());
        verify(overPaymentStrategy, never()).execute(any());
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando nenhuma estratégia de confirmação for encontrada")
    void execute_ShouldThrowNotFoundException_WhenNoStrategyConditionMet() {
        var sellerId = UUID.randomUUID().toString();
        var chargeId = UUID.randomUUID().toString();
        var paymentItem = new PaymentItemDomain(UUID.randomUUID().toString(), chargeId, null, BigDecimal.TEN, null);
        var paymentDomain = new PaymentDomain(sellerId, List.of(paymentItem));
        var seller = new SellerDomain(sellerId, "Vendedor");
        var charge = new ChargeDomain(chargeId, BigDecimal.TEN, BigDecimal.ZERO, emptyList());

        when(getByIdSellerGateway.getById(sellerId)).thenReturn(seller);
        when(getByIdChargeGateway.getById(chargeId)).thenReturn(charge);
        when(partialPaymentStrategy.condition(any(), any())).thenReturn(false);
        when(fullPaymentStrategy.condition(any(), any())).thenReturn(false);
        when(overPaymentStrategy.condition(any(), any())).thenReturn(false);

        assertThatThrownBy(() -> confirmPaymentUseCase.execute(paymentDomain))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Confirmação de pagamento");

        verify(partialPaymentStrategy, never()).execute(any());
        verify(fullPaymentStrategy, never()).execute(any());
        verify(overPaymentStrategy, never()).execute(any());
    }
}

