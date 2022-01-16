package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum TipoLancamento {

    DEBITO {
        @Override
        public Valor calcularSinal(Valor valor) {
            return new Valor(valor.getValor().negate().toString());
        }
    }, 
    
    CREDITO {
        @Override
        public Valor calcularSinal(Valor valor) {
            return new Valor(valor.getValor().abs().toString());
        }
    };

    public abstract Valor calcularSinal(Valor valor);

}
