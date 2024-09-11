package com.ruandob.challenge.api.charge.mappers;

import com.ruandob.challenge.api.charge.requests.ChargeRequest;
import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.infrastructure.mapstruct.BaseConfigMapper;
import com.ruandob.challenge.infrastructure.mapstruct.BaseRequestMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface ChargeRequestMapper extends BaseRequestMapper<ChargeRequest, ChargeDomain> {
}
