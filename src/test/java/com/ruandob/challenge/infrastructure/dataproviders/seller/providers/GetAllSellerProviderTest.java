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

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllSellerProviderTest {

    @Mock
    private JpaSellerRepository repository;

    @Spy
    private SellerDomainMapper mapper = Mappers.getMapper(SellerDomainMapper.class);

    @InjectMocks
    private GetAllSellerProvider getAllSellerProvider;

    @Test
    void getAll_ShouldReturnListOfMappedSellerDomains_WhenEntitiesArePresent() {
        var entity1 = new SellerEntity(UUID.randomUUID(), "Vendedor 1");
        var entity2 = new SellerEntity(UUID.randomUUID(), "Vendedor 2");

        when(repository.findAll()).thenReturn(List.of(entity1, entity2));

        List<SellerDomain> result = getAllSellerProvider.getAll();

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.get(0).id()).isEqualTo(entity1.getId().toString());
        assertThat(result.get(0).name()).isEqualTo(entity1.getName());
        assertThat(result.get(1).id()).isEqualTo(entity2.getId().toString());
        assertThat(result.get(1).name()).isEqualTo(entity2.getName());
    }

    @Test
    void getAll_ShouldReturnEmptyList_WhenNoEntitiesArePresent() {
        when(repository.findAll()).thenReturn(List.of());

        List<SellerDomain> result = getAllSellerProvider.getAll();

        assertThat(result).isNotNull().isEmpty();
    }
}
