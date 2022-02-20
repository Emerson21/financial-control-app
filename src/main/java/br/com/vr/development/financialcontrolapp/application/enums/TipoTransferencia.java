package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum TipoTransferencia {

    TED(new Valor("5")) {

        @Override
        public Valor aplicaTaxaNo(Valor valorTransferencia) {
            return taxa.adicionar(valorTransferencia);
        }
    },
    
    TEF(Valor.ZERO) {

        @Override
        public Valor aplicaTaxaNo(Valor valorTransferencia) {
            return taxa.adicionar(valorTransferencia);
        }
    };

    Valor taxa;

    TipoTransferencia(Valor taxa) {
        this.taxa = taxa;
    }

    public abstract Valor aplicaTaxaNo(Valor valorTransferencia);

}
