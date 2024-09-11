package com.ruandob.challenge.api.seller.requests;


import jakarta.validation.constraints.Size;

public record SellerRequest(
        @Size(max = 80, message = "{max_field_value}")
        String name
) {
}
