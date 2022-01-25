package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public interface ContaDestino extends Conta {

    void deposita(Valor valor);
    
}
