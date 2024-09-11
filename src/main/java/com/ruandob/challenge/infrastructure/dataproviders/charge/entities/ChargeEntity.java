package com.ruandob.challenge.infrastructure.dataproviders.charge.entities;

import com.ruandob.challenge.infrastructure.dataproviders.payment.entities.PaymentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "charges")
public class ChargeEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6953844514851245085L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name = "value", precision = 38, scale = 2)
    private BigDecimal value;

    @OneToMany(mappedBy = "charge")
    private List<PaymentEntity> payments;

    public BigDecimal getRemainingValue() {
        if (Objects.isNull(payments))
            return this.value;

        return payments.stream()
                .map(PaymentEntity::getValue)
                .reduce(this.value, BigDecimal::subtract);
    }
}
