package br.com.vr.development.financialcontrolapp.exception;

public class DepositoInicialException extends RuntimeException {

    public DepositoInicialException() {
        super("Valor inicial depositado menor que o permitido.");
    }
    
}
