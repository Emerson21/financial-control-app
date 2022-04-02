package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;

public abstract class Transacao {

    private Lancamento lancamento;

    public Transacao(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public Valor getValor() {
        return this.lancamento.getValor();
    }
}
