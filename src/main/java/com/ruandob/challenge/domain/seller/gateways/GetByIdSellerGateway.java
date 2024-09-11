package com.ruandob.challenge.domain.seller.gateways;

import com.ruandob.challenge.domain.seller.SellerDomain;

public interface GetByIdSellerGateway {

    SellerDomain getById(String id);
}
