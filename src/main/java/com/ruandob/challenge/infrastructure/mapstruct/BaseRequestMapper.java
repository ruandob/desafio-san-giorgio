package com.ruandob.challenge.infrastructure.mapstruct;

public interface BaseRequestMapper<R, D> {
    D toDomain(R request);
}
