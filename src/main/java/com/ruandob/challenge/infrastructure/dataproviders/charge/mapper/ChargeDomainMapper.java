package com.ruandob.challenge.infrastructure.dataproviders.charge.mapper;

import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.infrastructure.dataproviders.charge.entity.ChargeEntity;
import com.ruandob.challenge.infrastructure.mapstruct.BaseConfigMapper;
import com.ruandob.challenge.infrastructure.mapstruct.BaseDomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface ChargeDomainMapper extends BaseDomainMapper<ChargeDomain, ChargeEntity> {
}
