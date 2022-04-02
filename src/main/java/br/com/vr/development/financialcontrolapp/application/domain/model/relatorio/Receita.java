package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.exception.RendaException;

public class Receita extends Transacao {

    private Categoria categoria;

    public Receita(Lancamento lancamento, Categoria categoria) throws RendaException {
        super(lancamento);
        this.categoria = categoria;
        if (lancamento.isDebito()) throw new RendaException();
    }

    public enum Categoria {
        RESGATE_INVESTIMENTO, SALRIO, SALARIO
    }

}
