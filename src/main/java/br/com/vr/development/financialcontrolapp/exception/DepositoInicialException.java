package br.com.vr.development.financialcontrolapp.exception;

public class DepositoInicialException extends RuntimeException {

    public DepositoInicialException() {
        super("Valor minimo para abertura da conta corrente menor que o permitido");
    }
    
}
