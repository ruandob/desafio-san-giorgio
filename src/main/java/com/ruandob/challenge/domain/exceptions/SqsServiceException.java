package com.ruandob.challenge.domain.exceptions;

import java.io.Serial;

public class SqsServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 9156725700937259890L;
    private static final String DEFAULT_MESSAGE = "Ocorreu erro ao realizar o envio da mensagem";

    public SqsServiceException(String message) {
        super(DEFAULT_MESSAGE.concat(": ").concat(message));
    }
}
