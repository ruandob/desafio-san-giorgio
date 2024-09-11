package com.ruandob.challenge.domain.payment.usecases.impl;

import com.ruandob.challenge.domain.charge.gateways.GetByIdChargeGateway;
import com.ruandob.challenge.domain.exceptions.NotFoundException;
import com.ruandob.challenge.domain.payment.PaymentDomain;
import com.ruandob.challenge.domain.payment.strategies.ConfirmPaymentStrategy;
import com.ruandob.challenge.domain.payment.strategies.impl.ConfirmFullPaymentStrategyImpl;
import com.ruandob.challenge.domain.payment.strategies.impl.ConfirmOverPaymentStrategyImpl;
import com.ruandob.challenge.domain.payment.strategies.impl.ConfirmPartialPaymentStrategyImpl;
import com.ruandob.challenge.domain.payment.usecases.ConfirmPaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConfirmPaymentUseCaseImpl implements ConfirmPaymentUseCase {
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
        //buscar o seller id e validar se ele existe
        domain.paymentItens().forEach(payment -> {
            //buscar a cobrança pelo id payment.chargeId()
            var charge = getByIdChargeGateway.getById(payment.chargeId());

            getConfirmStrategies().stream()
                    .filter(confirm -> confirm.condition(payment.value(), charge.remainingValue()))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("Confirmação de pagamento"))
                    .execute(payment);
        });
    }


}
