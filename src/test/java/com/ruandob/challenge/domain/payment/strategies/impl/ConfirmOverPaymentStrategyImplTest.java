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
class ConfirmOverPaymentStrategyImplTest {

    @Mock
    private SendSqsPaymentGateway gateway;

    @InjectMocks
    private ConfirmOverPaymentStrategyImpl confirmOverPaymentStrategy;

    @Test
    @DisplayName("Deve retornar true quando o valor é maior que o valor restante")
    void shouldReturnTrueWhenValueIsGreaterThanRemainingValue() {
        var value = BigDecimal.valueOf(20);
        var remainingValue = BigDecimal.TEN;

        var result = confirmOverPaymentStrategy.condition(value, remainingValue);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Deve retornar false quando o valor é menor ou igual ao valor restante")
    void shouldReturnFalseWhenValueIsNotGreaterThanRemainingValue() {
        var value = BigDecimal.TEN;
        var remainingValue = BigDecimal.valueOf(20);

        var result = confirmOverPaymentStrategy.condition(value, remainingValue);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Deve enviar o pagamento para o gateway com o nome da fila")
    void shouldSendPaymentToGatewayWithQueueName() {
        var paymentItemDomain = new PaymentItemDomain("sellerId", "chargeId", "paymentId", BigDecimal.TEN, "status");

        confirmOverPaymentStrategy.execute(paymentItemDomain);
        verify(gateway, times(1)).send(any(), any());
    }
}
