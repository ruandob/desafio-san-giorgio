package com.ruandob.challenge.infrastructure.dataproviders.payment.mapper;

import com.ruandob.challenge.domain.payment.PaymentDomain;
import com.ruandob.challenge.infrastructure.dataproviders.payment.entity.PaymentEntity;
import com.ruandob.challenge.infrastructure.mapstruct.BaseConfigMapper;
import com.ruandob.challenge.infrastructure.mapstruct.BaseDomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface PaymentDomainMapper extends BaseDomainMapper<PaymentDomain, PaymentEntity> {
}
