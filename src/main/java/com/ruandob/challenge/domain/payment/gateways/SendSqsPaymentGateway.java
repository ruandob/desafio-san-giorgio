package com.ruandob.challenge.domain.payment.gateways;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;

public interface SendSqsPaymentGateway {

    void send(String queueName, PaymentItemDomain domain);
}
