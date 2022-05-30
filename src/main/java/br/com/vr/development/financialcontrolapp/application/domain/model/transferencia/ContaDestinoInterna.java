package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;

public interface ContaDestinoInterna extends ContaDestino {

    Banco getBanco();

    void deposita(Valor valor, TipoTransferencia tipoTransferencia);
    
}
