package br.com.vr.development.financialcontrolapp.exception;

public class LimiteExcedidoException extends DebitoException {

    public LimiteExcedidoException() {
        super("Limite de crédito excedido.");
    }
}
