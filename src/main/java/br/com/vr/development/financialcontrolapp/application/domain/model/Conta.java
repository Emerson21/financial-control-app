package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;

import java.util.List;

public abstract class Conta implements ContaDestino {

    public Valor getSaldo() {

        //utilizando Template Method e Hook Method em conjunto

        return !possuiSaldo()
                ? Valor.ZERO
                : getLancamentos().stream()
                .map(Lancamento::getValor)
                .reduce(Valor.ZERO, Valor::adicionar);
    }

    public abstract void adicionaDepositoInicialComoLancamento();

    public void adicionar(Lancamento lancamento) {
        getLancamentos().add(lancamento);
    }
    public boolean possuiSaldo() {
        return getLancamentos() != null && !getLancamentos().isEmpty();
    }

    public abstract List<Lancamento> getLancamentos();

}
