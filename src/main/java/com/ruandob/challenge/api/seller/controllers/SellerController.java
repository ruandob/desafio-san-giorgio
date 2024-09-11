package com.ruandob.challenge.api.seller.controllers;

import com.ruandob.challenge.api.seller.mappers.SellerRequestMapper;
import com.ruandob.challenge.api.seller.mappers.SellerResponseMapper;
import com.ruandob.challenge.api.seller.requests.SellerRequest;
import com.ruandob.challenge.api.validators.ValidateUUID;
import com.ruandob.challenge.domain.seller.usecases.CreateSellerUseCase;
import com.ruandob.challenge.domain.seller.usecases.GetAllSellerUseCase;
import com.ruandob.challenge.domain.seller.usecases.GetByIdSellerUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/sellers", produces = "application/json")
public record SellerController(
        CreateSellerUseCase createSellerUseCase,
        GetAllSellerUseCase getAllSellerUseCase,
        GetByIdSellerUseCase getByIdSellerUseCase,
        SellerRequestMapper requestMapper,
        SellerResponseMapper responseMapper
) {

    @PostMapping
    public ResponseEntity<Object> create(@Valid
                                         @RequestBody
                                         SellerRequest request) {
        var domain = createSellerUseCase.create(requestMapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMapper.toResponse(domain));
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        var domains = getAllSellerUseCase.getAll();
        var responses = domains.stream().map(responseMapper::toResponse).toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id")
                                          @ValidateUUID
                                          String id) {
        var domain = getByIdSellerUseCase.getById(id);
        return ResponseEntity.ok(responseMapper.toResponse(domain));
    }
}
