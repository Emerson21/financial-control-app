package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;

public interface ContaOrigem extends Conta {
    
    void saque(Valor valor);


}
