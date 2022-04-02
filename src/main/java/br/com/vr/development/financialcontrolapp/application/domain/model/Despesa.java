package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.relatorio.Transacao;
import br.com.vr.development.financialcontrolapp.exception.DespesaException;

public class Despesa extends Transacao {

    public Despesa(Lancamento lancamento, Categoria categoria) throws DespesaException {
        super(lancamento, categoria);
        if (lancamento.isCredito()) throw new DespesaException();
    }

}
