package br.com.vr.development.financialcontrolapp.application.domain.model;

public class Limite {

    private Valor limite;

    public Limite(Valor limite) {
        this.limite = limite;
    }

    public boolean naoPossuiSaldo(Valor valor) {
        return this.limite.menos(valor).ehNegativo();
    }
}
