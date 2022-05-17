package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Transferencia {

    private Valor valor;
    private ContaOrigem contaOrigem;
    private ContaDestino contaDestino;
    private TipoTransferencia tipoTransferencia;

    public void execute() throws SaldoInsuficienteException {
        this.contaOrigem.saque(tipoTransferencia.aplicaTaxaNo(valor), tipoTransferencia);
        this.contaDestino.deposita(valor, tipoTransferencia);
    }

}
