package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.exception.LimiteIndisponivelException;

public final class Limite {

    private Valor valor;

    public Limite(Valor valor) {
        this.valor = valor;
    }

    public Valor valor() {
        return valor;
    }

    public void saque(Valor valor) throws LimiteIndisponivelException {
        this.valor = this.valor.menos(valor);
        if (this.valor.ehNegativo()) {
            this.valor = this.valor.adicionar(valor);
            throw new LimiteIndisponivelException();
        }

    }
}
