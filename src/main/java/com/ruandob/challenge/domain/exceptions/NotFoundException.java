package com.ruandob.challenge.domain.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6975694590441932747L;
    private static final String MESSAGE = "Recurso não encontrado no sistema: ";

    public NotFoundException(String resource) {
        super(MESSAGE + resource);
    }
}
