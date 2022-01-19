package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum TipoLancamento {

    DEBITO {
        @Override
        public Valor calcularSinal(Valor valor) {
            return valor.negate();
        }
    }, 
    
    CREDITO {
        @Override
        public Valor calcularSinal(Valor valor) {
            return valor.abs();
        }
    };

    public abstract Valor calcularSinal(Valor valor);

}
