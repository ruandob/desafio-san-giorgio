package com.ruandob.challenge.infrastructure.dataproviders.payment.mappers;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.infrastructure.dataproviders.payment.entities.PaymentEntity;
import com.ruandob.challenge.infrastructure.mapstruct.BaseConfigMapper;
import com.ruandob.challenge.infrastructure.mapstruct.BaseDomainMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseConfigMapper.class)
public interface PaymentDomainMapper extends BaseDomainMapper<PaymentItemDomain, PaymentEntity> {

    @Mapping(target = "sellerId", source = "seller.id")
    @Mapping(target = "chargeId", source = "charge.id")
    PaymentItemDomain toDomain(PaymentEntity entity);

    @Mapping(target = "seller.id", source = "sellerId")
    @Mapping(target = "charge.id", source = "chargeId")
    PaymentEntity toEntity(PaymentItemDomain domain);
}
