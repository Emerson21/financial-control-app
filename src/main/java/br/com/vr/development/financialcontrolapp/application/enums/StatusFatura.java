package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum StatusFatura {
    PAGA, EM_ABERTO, PARCIALMENTE_PAGA;

    public static StatusFatura definir(Valor valorPagamento, Valor valorFatura) {
        if (valorPagamento.equals(valorFatura)) {
            return PAGA;
        }

        return PARCIALMENTE_PAGA;
    }

    public boolean isAberta() {
        return this == EM_ABERTO;
    }
}
