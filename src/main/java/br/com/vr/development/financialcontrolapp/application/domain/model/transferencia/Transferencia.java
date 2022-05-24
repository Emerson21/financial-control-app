package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Conta;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

public class Transferencia {

    private Valor valor;
    private Conta origem;
    private ContaDestino contaDestino;
    private TipoTransferencia tipoTransferencia;


    public Transferencia(Valor valor, Conta origem, ContaDestino contaDestino, TipoTransferencia tipoTransferencia) {
        this.valor = valor;
        this.origem = origem;
        this.contaDestino = contaDestino;
        this.tipoTransferencia = tipoTransferencia;
    }

    public void execute() throws SaldoInsuficienteException {
        origem.saque(tipoTransferencia.aplicaTaxaNo(valor), tipoTransferencia);
        contaDestino.deposita(valor, tipoTransferencia);
    }

}
