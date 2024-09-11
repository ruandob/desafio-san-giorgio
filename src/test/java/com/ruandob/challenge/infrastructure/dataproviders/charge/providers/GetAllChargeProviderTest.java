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
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllChargeProviderTest {

    @Mock
    private JpaChargeRepository repository;

    @Spy
    private PaymentDomainMapper paymentDomainMapper = Mappers.getMapper(PaymentDomainMapper.class);

    @Spy
    private ChargeDomainMapper mapper = new ChargeDomainMapperImpl(paymentDomainMapper);

    @InjectMocks
    private GetAllChargeProvider getAllChargeProvider;

    @Test
    @DisplayName("Deve retornar todas as cargas com sucesso")
    void shouldReturnAllChargesSuccessfully() {
        var chargeEntity1 = new ChargeEntity(UUID.randomUUID(), BigDecimal.TEN, Collections.emptyList());
        var chargeEntity2 = new ChargeEntity(UUID.randomUUID(), BigDecimal.valueOf(20), Collections.emptyList());
        var chargeDomain1 = new ChargeDomain(chargeEntity1.getId().toString(), chargeEntity1.getValue(), chargeEntity1.getRemainingValue(), Collections.emptyList());
        var chargeDomain2 = new ChargeDomain(chargeEntity2.getId().toString(), chargeEntity2.getValue(), chargeEntity2.getRemainingValue(), Collections.emptyList());

        when(repository.findAll()).thenReturn(List.of(chargeEntity1, chargeEntity2));

        var result = getAllChargeProvider.getAll();

        assertThat(result).containsExactly(chargeDomain1, chargeDomain2);
        verify(mapper, times(1)).toDomain(chargeEntity1);
        verify(mapper, times(1)).toDomain(chargeEntity2);
    }
}