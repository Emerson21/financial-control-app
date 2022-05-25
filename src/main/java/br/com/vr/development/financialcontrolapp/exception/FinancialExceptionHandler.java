package br.com.vr.development.financialcontrolapp.exception;

import br.com.vr.development.financialcontrolapp.common.ErroResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FinancialExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepositoInicialException.class)
    public ResponseEntity<Object> valorMinimoInvalido(DepositoInicialException valorMinimoInvalidoExcepton, WebRequest request) {
        return super.handleExceptionInternal(valorMinimoInvalidoExcepton,
            valorMinimoInvalidoExcepton.getMessage(), 
            HttpHeaders.EMPTY, 
            HttpStatus.UNPROCESSABLE_ENTITY, 
            request);
    }

    @ExceptionHandler(AgenciaInvalidaException.class)
    public ResponseEntity<Object> valorMinimoInvalido(AgenciaInvalidaException exception, WebRequest request) {
        return super.handleExceptionInternal(exception, 
            exception.getMessage(), 
            HttpHeaders.EMPTY, 
            HttpStatus.UNPROCESSABLE_ENTITY, 
            request);
    }

    @ExceptionHandler({ContaNotFoundException.class})
    public ResponseEntity<Object> contaNaoEncontrada(ContaNotFoundException contaNotFoundException, WebRequest request) {
        ErroResponse response = new ErroResponse(contaNotFoundException.getCodigo(), contaNotFoundException.getMensagem());

        return super.handleExceptionInternal(contaNotFoundException,
                response,
                HttpHeaders.EMPTY,
                HttpStatus.UNPROCESSABLE_ENTITY,
                request);
    }

    @ExceptionHandler({SaldoInsuficienteException.class})
    public ResponseEntity<Object> saldoInsuficienteException(SaldoInsuficienteException saldoInsuficienteException, WebRequest request) {
        ErroResponse response = new ErroResponse("-4", saldoInsuficienteException.getMessage());

        return super.handleExceptionInternal(saldoInsuficienteException,
                response,
                HttpHeaders.EMPTY,
                HttpStatus.UNPROCESSABLE_ENTITY,
                request);
    }
}
