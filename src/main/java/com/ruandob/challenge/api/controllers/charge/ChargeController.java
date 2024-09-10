package com.ruandob.challenge.api.controllers.charge;

import com.ruandob.challenge.api.controllers.charge.requests.ChargeRequest;
import com.ruandob.challenge.application.usecases.charge.CreateChargeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/charges", produces = "application/json")
public record ChargeController(
        CreateChargeUseCase createChargeUseCase
) {

    @PostMapping
    public ResponseEntity<Object> create(@Valid
                                         @RequestBody
                                         ChargeRequest request) {
        var response = createChargeUseCase.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
