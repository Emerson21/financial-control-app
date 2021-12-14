package br.com.vr.development.financialcontrolapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FinancialExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValorMinimoInvalidoExcepton.class)
    public ResponseEntity<Object> valorMinimoInvalido(ValorMinimoInvalidoExcepton valorMinimoInvalidoExcepton, WebRequest request) {
        
        return super.handleExceptionInternal(valorMinimoInvalidoExcepton, valorMinimoInvalidoExcepton.getMessage(), HttpHeaders.EMPTY, HttpStatus.UNPROCESSABLE_ENTITY, request);

    }

}
