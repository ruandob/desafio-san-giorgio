package com.ruandob.challenge.infrastructure.dataproviders.payment.provider;

import com.ruandob.challenge.domain.payment.PaymentDomain;
import com.ruandob.challenge.domain.payment.gateway.CreatePaymentGateway;
import com.ruandob.challenge.infrastructure.dataproviders.payment.mapper.PaymentDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.payment.repository.JpaPaymentRepository;
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
    public PaymentDomain create(PaymentDomain domain) {
        var entity = mapper.toEntity(domain);
        entity = repository.saveAndFlush(entity);
        log.info("[CreatePaymentProvider] Payment create with success: {}", entity);
        return mapper.toDomain(entity);
    }
}
