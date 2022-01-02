package br.com.vr.development.financialcontrolapp.exception;

public class SaldoIndisponivelException extends RuntimeException {

    public static final String MESSAGE = "Saldo indisponivel para realizar a operacao.";

    public SaldoIndisponivelException() {
        super(MESSAGE);
    }

}
