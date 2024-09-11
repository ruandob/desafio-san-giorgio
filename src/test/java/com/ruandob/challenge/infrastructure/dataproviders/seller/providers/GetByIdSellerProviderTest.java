package com.ruandob.challenge.infrastructure.dataproviders.seller.providers;

import com.ruandob.challenge.domain.exceptions.NotFoundException;
import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.infrastructure.dataproviders.seller.entities.SellerEntity;
import com.ruandob.challenge.infrastructure.dataproviders.seller.mappers.SellerDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.seller.repositories.JpaSellerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetByIdSellerProviderTest {

    @Mock
    private JpaSellerRepository repository;

    @Spy
    private SellerDomainMapper mapper = Mappers.getMapper(SellerDomainMapper.class);

    @InjectMocks
    private GetByIdSellerProvider getByIdSellerProvider;

    @Test
    @DisplayName("Deve retornar vendedor pelo id informado")
    void getById_ShouldReturnSellerDomain_WhenSellerExists() {
        UUID sellerId = UUID.randomUUID();
        SellerEntity entity = new SellerEntity(sellerId, "Vendedor Existente");
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(entity));

        SellerDomain result = getByIdSellerProvider.getById(sellerId.toString());

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(sellerId.toString());
        assertThat(result.name()).isEqualTo(entity.getName());
    }

    @Test
    @DisplayName("Deve retornar erro quando não encontrar vendedor")
    void getById_ShouldThrowNotFoundException_WhenSellerDoesNotExist() {
        String sellerId = UUID.randomUUID().toString();
        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> getByIdSellerProvider.getById(sellerId))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("vendedor")
                .hasMessageContaining(sellerId);
    }
}
