package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public enum TipoLancamento {

    DEBITO {
        @Override
        Valor calcularSinal(Valor valor) {
            return new Valor(valor.getValor().negate());
        }
    }, 
    
    CREDITO {
        @Override
        Valor calcularSinal(Valor valor) {
            return new Valor(valor.getValor().abs());
        }
    };

    abstract Valor calcularSinal(Valor valor);

}
