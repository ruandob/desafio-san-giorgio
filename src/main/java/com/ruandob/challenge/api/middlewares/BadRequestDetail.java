package com.ruandob.challenge.api.middlewares;

public record BadRequestDetail(String field, String message, Object value) {
}

