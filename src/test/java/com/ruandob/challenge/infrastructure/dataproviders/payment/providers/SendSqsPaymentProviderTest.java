package com.ruandob.challenge.infrastructure.dataproviders.payment.providers;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.infrastructure.dataproviders.payment.services.SqsSendPaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendSqsPaymentProviderTest {

    @Mock
    private SqsSendPaymentService service;

    @InjectMocks
    private SendSqsPaymentProvider sendSqsPaymentProvider;

    @Test
    @DisplayName("Deve enviar uma mensagem para o serviço SQS")
    void shouldSendMessageToSqsService() {
        var paymentItemDomain = new PaymentItemDomain("chargeId", "id", BigDecimal.TEN, "status");

        sendSqsPaymentProvider.send("queueName", paymentItemDomain);

        verify(service, times(1)).sendMessage("queueName", paymentItemDomain);
    }
}