package com.ruandob.challenge.infrastructure.mapstruct;

import java.util.List;

public interface BaseRequestMapper<R, D> {
    D toDomain(R request);
    List<D> toDomains(List<R> requests);
}
