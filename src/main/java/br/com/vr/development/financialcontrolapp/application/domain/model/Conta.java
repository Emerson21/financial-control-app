package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;

public interface Conta {

    Valor getSaldo();

    void adicionaDepositoInicialComoLancamento();

    void adicionarLancamento(Lancamento lancamento);
}
