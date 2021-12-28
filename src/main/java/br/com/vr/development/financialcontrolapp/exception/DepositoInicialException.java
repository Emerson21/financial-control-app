package br.com.vr.development.financialcontrolapp.exception;

public class DepositoInicialException extends RuntimeException {

    public static final String MESSAGE = "Valor inicial depositado menor que o permitido.";

    public DepositoInicialException() {
        super(MESSAGE);
    }
    
}