package com.ruandob.challenge.domain.payment.strategies.impl;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.domain.payment.gateways.SendSqsPaymentGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmFullPaymentStrategyImplTest {

    @Mock
    private SendSqsPaymentGateway gateway;

    @InjectMocks
    private ConfirmFullPaymentStrategyImpl confirmFullPaymentStrategy;

    @Test
    @DisplayName("Deve retornar true quando o valor é igual ao valor restante")
    void shouldReturnTrueWhenValueEqualsRemainingValue() {
        var value = BigDecimal.TEN;
        var remainingValue = BigDecimal.TEN;

        var result = confirmFullPaymentStrategy.condition(value, remainingValue);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando o valor é diferente do valor restante")
    void shouldReturnFalseWhenValueDoesNotEqualRemainingValue() {
        var value = BigDecimal.TEN;
        var remainingValue = BigDecimal.valueOf(20);

        var result = confirmFullPaymentStrategy.condition(value, remainingValue);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Deve enviar o pagamento para o gateway com o nome da fila")
    void shouldSendPaymentToGatewayWithQueueName() {
        var paymentItemDomain = new PaymentItemDomain("chargeId", "paymentId", BigDecimal.TEN, "status");

        confirmFullPaymentStrategy.execute(paymentItemDomain);
        verify(gateway, times(1)).send(any(), any());
    }
}