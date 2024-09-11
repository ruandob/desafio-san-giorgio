package com.ruandob.challenge.infrastructure.dataproviders.charge.providers;

import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.domain.exceptions.NotFoundException;
import com.ruandob.challenge.infrastructure.dataproviders.charge.entities.ChargeEntity;
import com.ruandob.challenge.infrastructure.dataproviders.charge.mappers.ChargeDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.charge.repositories.JpaChargeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetByIdChargeProviderTest {

    @Mock
    private JpaChargeRepository repository;

    @Spy
    private ChargeDomainMapper mapper = Mappers.getMapper(ChargeDomainMapper.class);

    @InjectMocks
    private GetByIdChargeProvider getByIdChargeProvider;

    @Test
    @DisplayName("Deve retornar uma carga pelo ID com sucesso")
    void shouldReturnChargeByIdSuccessfully() {
        var chargeId = UUID.randomUUID();
        var chargeEntity = new ChargeEntity(chargeId, BigDecimal.TEN, Collections.emptyList());
        var chargeDomain = new ChargeDomain(chargeId.toString(), BigDecimal.TEN, BigDecimal.TEN, Collections.emptyList());

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(chargeEntity));

        var result = getByIdChargeProvider.getById(chargeId.toString());

        assertThat(result.id()).isEqualTo(chargeDomain.id());
        assertThat(result.value()).isEqualTo(chargeDomain.value());
        assertThat(result.remainingValue()).isEqualTo(chargeDomain.remainingValue());
        verify(mapper, times(1)).toDomain(chargeEntity);
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando a carga não for encontrada")
    void shouldThrowNotFoundExceptionWhenChargeNotFound() {
        var chargeId = UUID.randomUUID().toString();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        var exception = assertThrows(NotFoundException.class, () -> getByIdChargeProvider.getById(chargeId));
        assertThat(exception.getMessage()).isEqualTo("Recurso não encontrado no sistema: cobrança");
        verify(repository, times(1)).findById(UUID.fromString(chargeId));
    }
}