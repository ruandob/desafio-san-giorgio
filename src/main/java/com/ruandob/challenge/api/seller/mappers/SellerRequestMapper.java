package com.ruandob.challenge.api.seller.mappers;

import com.ruandob.challenge.api.seller.requests.SellerRequest;
import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.infrastructure.mapstruct.BaseConfigMapper;
import com.ruandob.challenge.infrastructure.mapstruct.BaseRequestMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface SellerRequestMapper extends BaseRequestMapper<SellerRequest, SellerDomain> {
}
