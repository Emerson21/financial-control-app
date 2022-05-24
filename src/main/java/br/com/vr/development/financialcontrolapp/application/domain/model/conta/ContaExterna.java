package br.com.vr.development.financialcontrolapp.application.domain.model.conta;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.infrastructure.gateway.TransferenciaExternaClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContaExterna implements ContaDestino {

    private Banco banco;

    private AgenciaBancaria agenciaBancaria;

    private Integer numero;

    private Integer digito;

    private String nomeCorrentista;

    private Cpf cpf;

    private TransferenciaExternaClient client;

    @Override
    public Banco getBanco() {
        return this.banco;
    }

    @Override
    public void deposita(Valor valor, TipoTransferencia tipoTransferencia) {
        this.client.depositar(this);
    }
}
