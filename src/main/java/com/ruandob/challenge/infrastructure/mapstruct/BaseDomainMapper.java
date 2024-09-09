package com.ruandob.challenge.infrastructure.mapstruct;

public interface BaseDomainMapper<D, E> {
    D toDomain(E entity);
    E toEntity(D domain);
}
