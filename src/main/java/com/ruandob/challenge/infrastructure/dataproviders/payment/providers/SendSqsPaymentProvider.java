package com.ruandob.challenge.infrastructure.dataproviders.payment.providers;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.domain.payment.gateways.SendSqsPaymentGateway;
import com.ruandob.challenge.infrastructure.dataproviders.payment.services.SqsSendPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendSqsPaymentProvider implements SendSqsPaymentGateway {

    private final SqsSendPaymentService service;

    @Override
    public void send(String queueName, PaymentItemDomain domain) {
        service.sendMessage(queueName, domain);
    }
}
