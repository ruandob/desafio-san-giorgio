package com.ruandob.challenge.domain.payment.strategies;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;

import java.math.BigDecimal;

public interface ConfirmPaymentStrategy {

    boolean condition(BigDecimal value, BigDecimal remainingValue);

    void execute(PaymentItemDomain paymentItemDomain);
}
