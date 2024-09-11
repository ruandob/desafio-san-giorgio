package com.ruandob.challenge.infrastructure.dataproviders.seller.providers;

import com.ruandob.challenge.domain.seller.SellerDomain;
import com.ruandob.challenge.infrastructure.dataproviders.seller.entities.SellerEntity;
import com.ruandob.challenge.infrastructure.dataproviders.seller.mappers.SellerDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.seller.repositories.JpaSellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateSellerProviderTest {

    @Mock
    private JpaSellerRepository repository;

    @Spy
    private SellerDomainMapper mapper = Mappers.getMapper(SellerDomainMapper.class);

    @InjectMocks
    private CreateSellerProvider createSellerProvider;

    @Test
    void create_ShouldReturnMappedSellerDomain_WhenSellerEntityIsSaved() {
        var domain = new SellerDomain(null, "Vendedor");
        var entity = new SellerEntity(UUID.randomUUID(), "Vendedor");

        when(repository.saveAndFlush(any(SellerEntity.class))).thenReturn(entity);

        SellerDomain result = createSellerProvider.create(domain);

        verify(repository, times(1)).saveAndFlush(any(SellerEntity.class));

        assertThat(result).isNotNull();
        assertThat(result.id()).isNotNull();
        assertThat(result.name()).isEqualTo(domain.name());
    }
}