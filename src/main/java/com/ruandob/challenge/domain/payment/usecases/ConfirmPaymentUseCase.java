package com.ruandob.challenge.domain.payment.usecases;

import com.ruandob.challenge.domain.payment.PaymentDomain;

public interface ConfirmPaymentUseCase {
    void execute(PaymentDomain domain);
}
