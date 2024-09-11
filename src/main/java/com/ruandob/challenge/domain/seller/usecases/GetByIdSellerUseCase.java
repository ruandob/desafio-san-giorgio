package com.ruandob.challenge.domain.seller.usecases;

import com.ruandob.challenge.domain.seller.SellerDomain;

public interface GetByIdSellerUseCase {

    SellerDomain getById(String id);
}
