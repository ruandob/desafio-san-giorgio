package com.ruandob.challenge.api.controllers.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/payments", produces = "application/json")
public record PaymentController() {

    @PostMapping
    public ResponseEntity<Object> create() {
        return ResponseEntity.ok().build();
    }
}
