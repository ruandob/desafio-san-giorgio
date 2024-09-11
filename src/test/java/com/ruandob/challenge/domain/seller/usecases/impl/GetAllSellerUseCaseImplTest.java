package com.ruandob.challenge.domain.seller.usecases.impl;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.domain.seller.gateways.GetAllSellerGateway;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllSellerUseCaseImplTest {

    @Mock
    private GetAllSellerGateway gateway;

    @InjectMocks
    private GetAllSellerUseCaseImpl getAllSellerUseCase;

    @Test
    @DisplayName("Deve retornar uma lista de SellerDomain quando houver vendedores")
    void getAll_ShouldReturnListOfSellerDomain_WhenSellersExist() {
        SellerDomain seller1 = new SellerDomain("id1", "Vendedor 1");
        SellerDomain seller2 = new SellerDomain("id2", "Vendedor 2");
        List<SellerDomain> sellers = Arrays.asList(seller1, seller2);
        when(gateway.getAll()).thenReturn(sellers);

        List<SellerDomain> result = getAllSellerUseCase.getAll();


        assertThat(result)
                .isNotEmpty()
                .hasSize(2)
                .containsExactlyInAnyOrder(seller1, seller2);

        verify(gateway, times(1)).getAll();
    }

    @Test
    @DisplayName("Deve chamar o método do gateway uma vez quando chamado")
    void getAll_ShouldCallGatewayOnce_WhenCalled() {
        when(gateway.getAll()).thenReturn(List.of());

        getAllSellerUseCase.getAll();

        verify(gateway, times(1)).getAll();
    }
}