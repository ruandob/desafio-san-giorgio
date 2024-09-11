package com.ruandob.challenge.domain.seller.usecases;

import com.ruandob.challenge.domain.seller.SellerDomain;

import java.util.List;

public interface GetAllSellerUseCase {

    List<SellerDomain> getAll();
}
