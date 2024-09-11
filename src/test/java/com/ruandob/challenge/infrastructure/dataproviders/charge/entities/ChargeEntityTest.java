package com.ruandob.challenge.infrastructure.dataproviders.charge.entities;

import com.ruandob.challenge.infrastructure.dataproviders.payment.entities.PaymentEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ChargeEntityTest {

    @Test
    @DisplayName("Deve retornar o valor total quando não há pagamentos associados")
    void shouldReturnTotalValueWhenNoPayments() {
        var chargeEntity = new ChargeEntity(UUID.randomUUID(), BigDecimal.TEN, null);

        var remainingValue = chargeEntity.getRemainingValue();

        assertThat(remainingValue).isEqualTo(BigDecimal.TEN);
    }

    @Test
    @DisplayName("Deve retornar o valor restante após subtrair o total dos pagamentos")
    void shouldReturnRemainingValueAfterSubtractingPayments() {
        var chargeId = UUID.randomUUID();
        var payment1 = new PaymentEntity();
        payment1.setValue(BigDecimal.valueOf(3));

        var payment2 = new PaymentEntity();
        payment2.setValue(BigDecimal.valueOf(2));

        var chargeEntity = new ChargeEntity(chargeId, BigDecimal.valueOf(10), List.of(payment1, payment2));

        var remainingValue = chargeEntity.getRemainingValue();

        assertThat(remainingValue).isEqualTo(BigDecimal.valueOf(5));
    }
}