package com.ruandob.challenge.api.payment.mappers;

import com.ruandob.challenge.api.payment.requests.PaymentRequest;
import com.ruandob.challenge.domain.payment.PaymentDomain;
import com.ruandob.challenge.infrastructure.mapstruct.BaseConfigMapper;
import com.ruandob.challenge.infrastructure.mapstruct.BaseRequestMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface PaymentRequestMapper extends BaseRequestMapper<PaymentRequest, PaymentDomain> {
}
