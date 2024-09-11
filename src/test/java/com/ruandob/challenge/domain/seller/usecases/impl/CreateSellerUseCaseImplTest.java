package com.ruandob.challenge.domain.seller.usecases.impl;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.CreateSellerGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateSellerUseCaseImplTest {

    @Mock
    private CreateSellerGateway gateway;

    @InjectMocks
    private CreateSellerUseCaseImpl createSellerUseCase;

    @Test
    @DisplayName("Deve retornar um SellerDomain quando um novo vendedor é criado")
    void create_ShouldReturnSellerDomain_WhenNewSellerIsCreated() {
        SellerDomain domain = new SellerDomain(null, "Novo Vendedor");
        SellerDomain savedDomain = new SellerDomain("id", "Novo Vendedor");
        when(gateway.create(domain)).thenReturn(savedDomain);

        SellerDomain result = createSellerUseCase.create(domain);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(savedDomain.id());
        assertThat(result.name()).isEqualTo(savedDomain.name());

        verify(gateway, times(1)).create(domain);
    }
}