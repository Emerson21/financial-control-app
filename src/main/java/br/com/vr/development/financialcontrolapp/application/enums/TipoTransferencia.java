package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum TipoTransferencia {
    
    TED {
        @Override
        public Valor taxa() {
            return new Valor("5");
        }
    },
    
    TEF {
        
        @Override
        public Valor taxa() {
            return Valor.ZERO;
        }
    };

    public abstract Valor taxa();
}
