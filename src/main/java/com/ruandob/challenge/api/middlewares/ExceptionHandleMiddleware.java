package com.ruandob.challenge.api.middlewares;

import com.ruandob.challenge.api.details.BadRequestDetail;
import com.ruandob.challenge.domain.exceptions.NotFoundException;
import com.ruandob.challenge.domain.exceptions.SqsServiceException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class ExceptionHandleMiddleware {

    private static final String FIELD_ERRORS = "field_errors";
    private static final String MESSAGE_FIELD_ERRORS = "field errors";

    private static final String MESSAGE = "message";

    private static final String UNHANDLED_EXCEPTION = "unhandled_exception";

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<BadRequestDetail> details = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            var campo = fieldError.getField();
            return new BadRequestDetail(toSnakeCase(campo), fieldError.getDefaultMessage(), fieldError.getRejectedValue());
        }).toList();

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, MESSAGE_FIELD_ERRORS);
        problemDetail.setProperty(FIELD_ERRORS, details);
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
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, MESSAGE_FIELD_ERRORS);
        problemDetail.setProperty(FIELD_ERRORS, details);
        return problemDetail;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setProperty(MESSAGE, ex.getMessage());
        return problemDetail;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public ProblemDetail handleNoResourceFoundException(NoResourceFoundException ex) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setProperty(MESSAGE, ex.getMessage());
        return problemDetail;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException ex) {
        List<BadRequestDetail> details = ex.getAllValidationResults().stream().map(fieldError -> {
            var field = getFieldName(fieldError.getMethodParameter());
            var mensagem = getMessage(fieldError.getResolvableErrors());
            var value = Optional.ofNullable(fieldError.getArgument()).toString();
            return new BadRequestDetail(toSnakeCase(field), mensagem, value);
        }).toList();
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, MESSAGE_FIELD_ERRORS);
        problemDetail.setProperty(FIELD_ERRORS, details);
        return problemDetail;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SqsServiceException.class)
    public ProblemDetail handleSqsServiceException(SqsServiceException ex) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setProperty(MESSAGE, ex.getMessage());
        return problemDetail;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {
        log.error("[INTERNAL_SERVER_ERRO] Ex: {}", ex.getMessage());
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setProperty(UNHANDLED_EXCEPTION, ex.getMessage());
        return problemDetail;
    }


    private String getFieldName(MethodParameter methodParameter) {
        if (Objects.isNull(methodParameter))
            return "";

        return methodParameter.getParameterName();
    }

    private String getMessage(List<MessageSourceResolvable> messageSourceResolvables) {
        if (CollectionUtils.isEmpty(messageSourceResolvables))
            return "";

        return messageSourceResolvables.stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse("");
    }

    private String toSnakeCase(String input) {
        if (Objects.isNull(input))
            return "";

        String[] words = input.split("(?=[A-Z])");
        return String.join("_", words).toLowerCase();
    }
}
