package com.ruandob.challenge.domain.payment.usecases.impl;

import com.ruandob.challenge.domain.charge.gateways.GetByIdChargeGateway;
import com.ruandob.challenge.domain.exceptions.NotFoundException;
import com.ruandob.challenge.domain.payment.PaymentDomain;
import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.domain.payment.strategies.ConfirmPaymentStrategy;
import com.ruandob.challenge.domain.payment.strategies.impl.ConfirmFullPaymentStrategyImpl;
import com.ruandob.challenge.domain.payment.strategies.impl.ConfirmOverPaymentStrategyImpl;
import com.ruandob.challenge.domain.payment.strategies.impl.ConfirmPartialPaymentStrategyImpl;
import com.ruandob.challenge.domain.payment.usecases.ConfirmPaymentUseCase;
import com.ruandob.challenge.domain.seller.gateways.GetByIdSellerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConfirmPaymentUseCaseImpl implements ConfirmPaymentUseCase {

    private final GetByIdSellerGateway getByIdSellerGateway;
    private final GetByIdChargeGateway getByIdChargeGateway;
    private final ConfirmPartialPaymentStrategyImpl partialPaymentStrategy;
    private final ConfirmFullPaymentStrategyImpl fullPaymentStrategy;
    private final ConfirmOverPaymentStrategyImpl overPaymentStrategy;


    private List<ConfirmPaymentStrategy> getConfirmStrategies() {
        return List.of(partialPaymentStrategy,
                fullPaymentStrategy,
                overPaymentStrategy);
    }

    @Override
    @Transactional
    public void execute(PaymentDomain domain) {
        var seller = getByIdSellerGateway.getById(domain.sellerId());
        var paymentItens = setSellerId(seller.id(), domain.paymentItens());
        paymentItens.forEach(payment -> {
            var charge = getByIdChargeGateway.getById(payment.chargeId());

            getConfirmStrategies().stream()
                    .filter(confirm -> confirm.condition(payment.value(), charge.remainingValue()))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Confirmação de pagamento"))
                    .execute(payment);
        });
    }

    private List<PaymentItemDomain> setSellerId(String sellerId, List<PaymentItemDomain> payments) {
        return payments.stream()
                .map(payment -> payment.addSellerId(sellerId))
                .toList();
    }

}
