package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.exception.LimiteExcedidoException;

public final class Limite {

    private Valor valor;

    public Limite(Valor valor) {
        this.valor = valor;
    }

    public Valor valor() {
        return valor;
    }

    public void saque(Valor valor) throws LimiteExcedidoException {
        if (this.valor.menos(valor).ehNegativo()) {
            throw new LimiteExcedidoException();
        }

        this.valor = this.valor.menos(valor);
    }
}
