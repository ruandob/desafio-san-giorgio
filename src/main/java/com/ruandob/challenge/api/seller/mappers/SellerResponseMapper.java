package com.ruandob.challenge.api.seller.mappers;

import com.ruandob.challenge.api.seller.responses.SellerResponse;
import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.infrastructure.mapstruct.BaseConfigMapper;
import com.ruandob.challenge.infrastructure.mapstruct.BaseResponseMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseConfigMapper.class)
public interface SellerResponseMapper extends BaseResponseMapper<SellerResponse, SellerDomain> {
}
