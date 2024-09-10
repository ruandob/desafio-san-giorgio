package com.ruandob.challenge.infrastructure.dataproviders.payment.entity;

import com.ruandob.challenge.infrastructure.dataproviders.charge.entity.ChargeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
public class PaymentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 4247792405139753115L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "charge_id")
    private ChargeEntity charge;
}
