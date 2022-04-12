package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;

public class Fatura extends Conta {

    private Limite limite;

    public Fatura(Limite limite) {
        this.limite = limite;
    }

    @Override
    public void saque(Valor valor, TipoTransferencia tipoTransferencia) {
        if (limite.naoPossuiSaldo(valor)) { //acredito que nao esteja em um linguagem ubiqua
            throw new SaldoInsuficienteException("Limite insuficiente para realizar transacao");
        }

        criarLancamento(valor, new Descricao("Compra realizada no cartao de credito"));
    }

    private void criarLancamento(Valor valor, Descricao descricao) {
        adicionar(Lancamento.criaLancamentoNegativo(valor, descricao, this, TipoTransferencia.CARTAO_CREDITO));
    }
}
