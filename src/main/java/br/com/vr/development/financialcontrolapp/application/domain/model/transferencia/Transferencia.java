package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Transferencia {

    private Valor valor;
    private ContaOrigem contaOrigem;
    private ContaDestino contaDestino;

    public void execute(TipoTransferencia tipoTransferencia) {

        this.contaOrigem.saque(valor.adicionar(tipoTransferencia.taxa()));
        this.contaDestino.deposita(valor);

    }

}
