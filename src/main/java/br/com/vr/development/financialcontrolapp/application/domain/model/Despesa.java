package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.exception.DespesaException;

public class Despesa {

    private Lancamento lancamento;

    private Categoria categoria;

    public Despesa(Lancamento lancamento, Categoria categoria) throws DespesaException {
        if (lancamento.isCredito()) throw new DespesaException();
        this.lancamento = lancamento;
        this.categoria = categoria;
    }

    public Valor getValor() {
        return this.lancamento.getValor();
    }


    public enum Categoria {
        ALIMENTACAO, CASA, ESTUDO
    }
}
