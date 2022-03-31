package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.exception.RendaException;

public class Receita {

    private Lancamento lancamento;

    private Categoria categoria;

    public Receita(Lancamento lancamento, Categoria categoria) throws RendaException {
        if (lancamento.isDebito()) throw new RendaException();

        //super class
        this.lancamento = lancamento;
        this.categoria = categoria;
    }

    public enum Categoria {
        RESGATE_INVESTIMENTO, SALRIO, SALARIO
    }

    //super class
    public Valor getValor() {
        return this.lancamento.getValor();
    }
}
