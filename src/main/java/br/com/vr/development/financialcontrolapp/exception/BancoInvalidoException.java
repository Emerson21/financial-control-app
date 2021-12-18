package br.com.vr.development.financialcontrolapp.exception;

public class BancoInvalidoException extends RuntimeException {
    
    public BancoInvalidoException() {
        super("Codigo do banco informado invalido.");
    }


}
