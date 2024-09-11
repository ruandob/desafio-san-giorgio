package com.ruandob.challenge.api.charge.mappers;

import com.ruandob.challenge.api.charge.responses.ChargeResponse;
import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.infrastructure.mapstruct.BaseConfigMapper;
import com.ruandob.challenge.infrastructure.mapstruct.BaseResponseMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface ChargeResponseMapper extends BaseResponseMapper<ChargeResponse, ChargeDomain> {
}
