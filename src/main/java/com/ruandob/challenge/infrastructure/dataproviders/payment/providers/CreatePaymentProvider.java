package com.ruandob.challenge.infrastructure.dataproviders.payment.providers;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.domain.payment.gateways.CreatePaymentGateway;
import com.ruandob.challenge.infrastructure.dataproviders.payment.mappers.PaymentDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.payment.repositories.JpaPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePaymentProvider implements CreatePaymentGateway {

    private final JpaPaymentRepository repository;
    private final PaymentDomainMapper mapper;

    @Override
    public PaymentItemDomain create(PaymentItemDomain domain) {
        var entity = mapper.toEntity(domain);
        entity = repository.saveAndFlush(entity);
        log.info("[CreatePaymentProvider] Payment create with success: {}", entity);
        return mapper.toDomain(entity);
    }
}
