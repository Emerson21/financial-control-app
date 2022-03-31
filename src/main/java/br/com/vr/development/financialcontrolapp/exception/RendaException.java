package br.com.vr.development.financialcontrolapp.exception;

public class RendaException extends Exception {

    public RendaException() {
        super("Renda nao pode ter um valor negativo.");

    }
}
