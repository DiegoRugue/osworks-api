package com.algaworks.osworks.osworksapi.api.exceptionhandler;

import com.algaworks.osworks.osworksapi.domain.exception.DomainException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Object> handleDomain(DomainException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;

        var validadeError = new ValidateError();
        validadeError.setStatus(status.value());
        validadeError.setTitulo(ex.getMessage());
        validadeError.setDataHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, validadeError, new HttpHeaders(), status, request);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campos = new ArrayList<ValidateError.Campo>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();

            campos.add(new ValidateError.Campo(nome, mensagem));
        }

        var validateError = new ValidateError();
        validateError.setStatus(status.value());
        validateError.setTitulo("Campos não preenchidos corretamente");
        validateError.setDataHora(OffsetDateTime.now());
        validateError.setCampos(campos);

        return super.handleExceptionInternal(ex, validateError, headers, status, request);
    }
}
