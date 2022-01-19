package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public interface ContaDestino {

    void deposita(Valor valor);
    
}
