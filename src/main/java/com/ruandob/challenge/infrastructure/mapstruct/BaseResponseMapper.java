package com.ruandob.challenge.infrastructure.mapstruct;

public interface BaseResponseMapper<R, D> {
    R toResponse(D domain);
}
