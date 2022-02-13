package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum TipoTransferencia {
    
    TED {
        @Override
        Valor taxa() {
            return new Valor("5");
        }

        @Override
        public Valor aplicaTaxaNo(Valor valorTransferencia) {
            return this.taxa().adicionar(valorTransferencia);
        }
    },
    
    TEF {

        @Override
        Valor taxa() {
            return Valor.ZERO;
        }

        @Override
        public Valor aplicaTaxaNo(Valor valorTransferencia) {
            return this.taxa().adicionar(valorTransferencia);
        }
    };

    abstract Valor taxa();

    public abstract Valor aplicaTaxaNo(Valor valorTransferencia);


}
