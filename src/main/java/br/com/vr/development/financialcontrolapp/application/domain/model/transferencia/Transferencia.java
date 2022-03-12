package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Transferencia {

    private Valor valor;
    private ContaOrigem contaOrigem;
    private ContaDestino contaDestino;
    private TipoTransferencia tipoTransferencia;

    public void execute() {

        this.contaOrigem.saque(tipoTransferencia.aplicaTaxaNo(valor), tipoTransferencia);
        this.contaDestino.deposita(valor, tipoTransferencia);

    }

}
