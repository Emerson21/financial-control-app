package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum TipoTransferencia {

    TED(new Valor("5")),
    TEF(Valor.ZERO);


    private Valor taxa;

    TipoTransferencia(Valor taxa) {
        this.taxa = taxa;
    }

    public Valor aplicaTaxaNo(Valor valorTransferencia) {
        return taxa.adicionar(valorTransferencia);
    }

}
