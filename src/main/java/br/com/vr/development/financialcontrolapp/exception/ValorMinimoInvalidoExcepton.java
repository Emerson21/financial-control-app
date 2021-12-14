package br.com.vr.development.financialcontrolapp.exception;

public class ValorMinimoInvalidoExcepton extends RuntimeException {

    public ValorMinimoInvalidoExcepton() {
        super("Valor minimo para abertura da conta corrente menor que 50.");
    }
    
}
