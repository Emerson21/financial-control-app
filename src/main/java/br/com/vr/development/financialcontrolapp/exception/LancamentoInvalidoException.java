package br.com.vr.development.financialcontrolapp.exception;

public class LancamentoInvalidoException extends RuntimeException  {
    
    public LancamentoInvalidoException() {
        super("Lancamento invalido.");
    }

}
