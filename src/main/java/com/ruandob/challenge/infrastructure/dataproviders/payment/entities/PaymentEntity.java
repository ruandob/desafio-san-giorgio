package com.ruandob.challenge.infrastructure.dataproviders.payment.entities;

import com.ruandob.challenge.infrastructure.dataproviders.charge.entities.ChargeEntity;
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
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(name = "value", precision = 38, scale = 2)
    private BigDecimal value;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "charge_id")
    private ChargeEntity charge;
}
