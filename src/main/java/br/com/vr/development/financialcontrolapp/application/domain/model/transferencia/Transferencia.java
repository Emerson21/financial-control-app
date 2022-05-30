package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;

public abstract class Transferencia implements ITransferencia {

    Valor valor;
    ContaOrigem origem;
    ContaDestinoInterna contaDestino;
    TipoTransferencia tipoTransferencia;

    public Transferencia(Valor valor, ContaOrigem origem, ContaDestinoInterna contaDestino, TipoTransferencia tipoTransferencia) {
        this.valor = valor;
        this.origem = origem;
        this.contaDestino = contaDestino;
        this.tipoTransferencia = tipoTransferencia;
    }

}
