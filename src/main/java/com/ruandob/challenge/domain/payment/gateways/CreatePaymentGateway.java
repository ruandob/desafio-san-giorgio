package com.ruandob.challenge.domain.payment.gateways;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;

public interface CreatePaymentGateway {

    PaymentItemDomain create(PaymentItemDomain domain);
}
