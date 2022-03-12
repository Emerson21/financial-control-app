package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;

public interface ContaDestino {

    void deposita(Valor valor, TipoTransferencia tipoTransferencia);
    
}
