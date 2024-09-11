package com.ruandob.challenge.domain.seller.usecases.impl;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.GetByIdSellerGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetByIdSellerUseCaseImplTest {

    @Mock
    private GetByIdSellerGateway gateway;

    @InjectMocks
    private GetByIdSellerUseCaseImpl getByIdSellerUseCase;

    @Test
    @DisplayName("Deve retornar SellerDomain quando o vendedor existe")
    void getById_ShouldReturnSellerDomain_WhenSellerExists() {
        String sellerId = UUID.randomUUID().toString();
        SellerDomain sellerDomain = new SellerDomain(sellerId, "Vendedor Existente");
        when(gateway.getById(sellerId)).thenReturn(sellerDomain);

        SellerDomain result = getByIdSellerUseCase.getById(sellerId);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(sellerId);
        assertThat(result.name()).isEqualTo(sellerDomain.name());

        verify(gateway, times(1)).getById(sellerId);
    }

    @Test
    @DisplayName("Deve chamar o método do gateway uma vez quando chamado")
    void getById_ShouldCallGatewayOnce_WhenCalled() {
        String sellerId = UUID.randomUUID().toString();
        when(gateway.getById(sellerId)).thenReturn(new SellerDomain(sellerId, "Vendedor"));

        getByIdSellerUseCase.getById(sellerId);

        verify(gateway, times(1)).getById(sellerId);
    }
}