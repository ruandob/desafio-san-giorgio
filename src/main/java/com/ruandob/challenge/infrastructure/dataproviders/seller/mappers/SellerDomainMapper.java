package com.ruandob.challenge.infrastructure.dataproviders.seller.mappers;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.infrastructure.dataproviders.seller.entities.SellerEntity;
import com.ruandob.challenge.infrastructure.mapstruct.BaseConfigMapper;
import com.ruandob.challenge.infrastructure.mapstruct.BaseDomainMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface SellerDomainMapper extends BaseDomainMapper<SellerDomain, SellerEntity> {
}
