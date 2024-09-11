package com.ruandob.challenge.domain.payment.strategies.impl;

import com.ruandob.challenge.domain.payment.strategies.ConfirmPaymentStrategy;
import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.domain.payment.gateways.SendSqsPaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ConfirmFullPaymentStrategyImpl implements ConfirmPaymentStrategy {

    @Value("${queueName.full}")
    private String queueName;

    private final SendSqsPaymentGateway gateway;

    @Override
    public boolean condition(BigDecimal value, BigDecimal remainingValue) {
        return value.compareTo(remainingValue) == 0;
    }

    @Override
    public void execute(PaymentItemDomain paymentItemDomain) {
        gateway.send(queueName, paymentItemDomain);
    }
}
