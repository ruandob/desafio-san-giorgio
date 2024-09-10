package com.ruandob.challenge.api.middlewares;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandleMiddleware {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<BadRequestDetail> details = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            var campo = fieldError.getField();
            return new BadRequestDetail(toSnakeCase(campo), fieldError.getDefaultMessage(), fieldError.getRejectedValue());
        }).toList();

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "field errors");
        problemDetail.setProperty("field_errors", details);
        return problemDetail;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(ConstraintViolationException ex) {
        List<BadRequestDetail> details = ex.getConstraintViolations().stream().map(fieldError -> {
            var campo = fieldError.getPropertyPath().toString();
            return new BadRequestDetail(toSnakeCase(campo), fieldError.getMessage(), fieldError.getInvalidValue());
        }).toList();
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "field errors");
        problemDetail.setProperty("field_errors", details);
        return problemDetail;
    }

    private String toSnakeCase(String input) {
        String[] words = input.split("(?=[A-Z])");
        return String.join("_", words).toLowerCase();
    }
}
