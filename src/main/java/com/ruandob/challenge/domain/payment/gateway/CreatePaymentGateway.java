package com.ruandob.challenge.domain.payment.gateway;

import com.ruandob.challenge.domain.payment.PaymentDomain;

public interface CreatePaymentGateway {

    PaymentDomain create(PaymentDomain domain);
}
