package br.com.vr.development.financialcontrolapp.exception;


//PQ utilizar uma RuntimeException??
public class DespesaException extends Exception {

    public DespesaException() {
        super("Despesa nao pode ter um valor positivo.");
    }

}
