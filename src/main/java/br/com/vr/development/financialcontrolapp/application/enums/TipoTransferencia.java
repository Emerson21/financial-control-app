package br.com.vr.development.financialcontrolapp.application.enums;

import java.math.BigDecimal;

public enum TipoTransferencia {
    TED {
        @Override
        public BigDecimal taxa() {
            return new BigDecimal("5");
        }

 
    },
    
    TEF {
        @Override
        public BigDecimal taxa() {
            return BigDecimal.ZERO;
        }
    };


    public abstract BigDecimal taxa();

}
