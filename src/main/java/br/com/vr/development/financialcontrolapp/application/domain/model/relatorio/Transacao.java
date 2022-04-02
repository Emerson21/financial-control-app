package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.Getter;

public abstract class Transacao {

    private Lancamento lancamento;

    @Getter
    private Categoria categoria;

    public Transacao(Lancamento lancamento, Categoria categoria) {
        this.lancamento = lancamento;
        this.categoria = categoria;
    }

    public Valor getValor() {
        return this.lancamento.getValor();
    }

    public enum Categoria {
        ALIMENTACAO, CASA, ESTUDO, RESGATE_INVESTIMENTO, SALARIO
    }
}
