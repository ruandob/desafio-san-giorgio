package com.ruandob.challenge.domain.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6975694590441932747L;
    private static final String MESSAGE = "Recurso não encontrado no sistema: ";
    private static final String INVALID_ID = ", id informado não encontrado: ";

    public NotFoundException(String resource) {
        super(MESSAGE + resource);
    }

    public NotFoundException(String resource, String id) {
        super(MESSAGE + resource + INVALID_ID + id);
    }
}
