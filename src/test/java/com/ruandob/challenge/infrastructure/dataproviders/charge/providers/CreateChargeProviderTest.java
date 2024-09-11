package com.ruandob.challenge.infrastructure.dataproviders.charge.providers;

import com.ruandob.challenge.domain.charge.ChargeDomain;
import com.ruandob.challenge.infrastructure.dataproviders.charge.entities.ChargeEntity;
import com.ruandob.challenge.infrastructure.dataproviders.charge.mappers.ChargeDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.charge.mappers.ChargeDomainMapperImpl;
import com.ruandob.challenge.infrastructure.dataproviders.charge.repositories.JpaChargeRepository;
import com.ruandob.challenge.infrastructure.dataproviders.payment.mappers.PaymentDomainMapper;
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
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateChargeProviderTest {

    @Mock
    private JpaChargeRepository repository;

    @Spy
    private PaymentDomainMapper paymentDomainMapper = Mappers.getMapper(PaymentDomainMapper.class);

    @Spy
    private ChargeDomainMapper mapper = new ChargeDomainMapperImpl(paymentDomainMapper);

    @InjectMocks
    private CreateChargeProvider createChargeProvider;

    @Test
    @DisplayName("Deve criar uma carga com sucesso")
    void shouldCreateChargeSuccessfully() {
        var chargeDomain = new ChargeDomain(UUID.randomUUID().toString(), BigDecimal.TEN, BigDecimal.ONE, Collections.emptyList());
        var chargeEntity = new ChargeEntity(UUID.randomUUID(), BigDecimal.TEN, Collections.emptyList());

        when(repository.saveAndFlush(any(ChargeEntity.class))).thenReturn(chargeEntity);

        var result = createChargeProvider.create(chargeDomain);

        assertThat(result.id()).isEqualTo(chargeEntity.getId().toString());
        assertThat(result.value()).isEqualTo(chargeEntity.getValue());
        assertThat(result.remainingValue()).isEqualTo(chargeEntity.getRemainingValue());
        verify(mapper, times(1)).toEntity(chargeDomain);
        verify(mapper, times(1)).toDomain(chargeEntity);
    }
}
