package com.ruandob.challenge.api.charge.controllers;

import com.ruandob.challenge.api.charge.mappers.ChargeRequestMapper;
import com.ruandob.challenge.api.charge.mappers.ChargeResponseMapper;
import com.ruandob.challenge.api.charge.requests.ChargeRequest;
import com.ruandob.challenge.api.validators.ValidateUUID;
import com.ruandob.challenge.domain.charge.usecases.CreateChargeUseCase;
import com.ruandob.challenge.domain.charge.usecases.GetAllChargeUseCase;
import com.ruandob.challenge.domain.charge.usecases.GetByIdChargeUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/charges", produces = "application/json")
public record ChargeController(
        CreateChargeUseCase createChargeUseCase,
        GetAllChargeUseCase getAllChargeUseCase,
        GetByIdChargeUseCase getByIdChargeUseCase,
        ChargeRequestMapper requestMapper,
        ChargeResponseMapper responseMapper
) {

    @PostMapping
    public ResponseEntity<Object> create(@Valid
                                         @RequestBody
                                         ChargeRequest request) {
        var domain = requestMapper.toDomain(request);
        domain = createChargeUseCase.create(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toResponse(domain));
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        var domains = getAllChargeUseCase.getAll();
        var responses = domains.stream().map(responseMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id")
                                          @ValidateUUID
                                          String id) {
        var domain = getByIdChargeUseCase.getById(id);
        return ResponseEntity.ok(responseMapper.toResponse(domain));
    }
}
