package com.ruandob.challenge.domain.seller;


import lombok.Builder;

@Builder
public record SellerDomain(String id,
                           String name) {
}
