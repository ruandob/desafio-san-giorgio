package com.ruandob.challenge.infrastructure.dataproviders.payment.providers;

import com.ruandob.challenge.domain.payment.PaymentItemDomain;
import com.ruandob.challenge.infrastructure.dataproviders.payment.entities.PaymentEntity;
import com.ruandob.challenge.infrastructure.dataproviders.payment.mappers.PaymentDomainMapper;
import com.ruandob.challenge.infrastructure.dataproviders.payment.repositories.JpaPaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePaymentProviderTest {

    @Mock
    private JpaPaymentRepository repository;

    @Spy
    private PaymentDomainMapper mapper = Mappers.getMapper(PaymentDomainMapper.class);

    @InjectMocks
    private CreatePaymentProvider createPaymentProvider;

    @Test
    @DisplayName("Deve criar um pagamento com sucesso")
    void shouldCreatePaymentSuccessfully() {
        var paymentItemDomain = new PaymentItemDomain(UUID.randomUUID().toString(), UUID.randomUUID().toString(), null, BigDecimal.TEN, "status");
        var paymentEntity = new PaymentEntity(UUID.randomUUID(), BigDecimal.TEN, "status", null, null);

        when(repository.saveAndFlush(any())).thenReturn(paymentEntity);

        var result = createPaymentProvider.create(paymentItemDomain);

        assertThat(result.id()).isEqualTo(paymentEntity.getId().toString());
        assertThat(result.value()).isEqualTo(paymentEntity.getValue());
        assertThat(result.status()).isEqualTo(paymentEntity.getStatus());
        verify(mapper, times(1)).toEntity(paymentItemDomain);
        verify(mapper, times(1)).toDomain(paymentEntity);
    }
}

