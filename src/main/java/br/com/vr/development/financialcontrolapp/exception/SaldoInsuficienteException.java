package br.com.vr.development.financialcontrolapp.exception;

public class SaldoInsuficienteException extends DebitoException {

    public static final String MESSAGE = "Saldo indisponivel para realizar a operacao.";

    public SaldoInsuficienteException() {
        super(MESSAGE);
    }

    public SaldoInsuficienteException(String message) {
        super(message);
    }
}
