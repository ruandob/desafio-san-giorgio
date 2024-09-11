package com.ruandob.challenge.api.payment.controllers;

import com.ruandob.challenge.api.payment.mappers.PaymentRequestMapper;
import com.ruandob.challenge.api.payment.requests.PaymentRequest;
import com.ruandob.challenge.domain.payment.usecases.ConfirmPaymentUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/payments", produces = "application/json")
public record PaymentController(
        ConfirmPaymentUseCase confirmPaymentUseCase,
        PaymentRequestMapper requestMapper
) {

    @PostMapping
    public ResponseEntity<Object> confirm(@Valid @RequestBody PaymentRequest request) {
        var domain = requestMapper.toDomain(request);
        confirmPaymentUseCase.execute(domain);
        return ResponseEntity.noContent().build();
    }
}
