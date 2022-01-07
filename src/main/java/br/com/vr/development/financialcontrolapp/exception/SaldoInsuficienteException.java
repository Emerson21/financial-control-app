package br.com.vr.development.financialcontrolapp.exception;

public class SaldoInsuficienteException extends RuntimeException {

    public static final String MESSAGE = "Saldo indisponivel para realizar a operacao.";

    public SaldoInsuficienteException() {
        super(MESSAGE);
    }

}
