package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.exception.RendaException;

public class Receita extends Transacao {

    public Receita(Lancamento lancamento, Categoria categoria) throws RendaException {
        super(lancamento, categoria);
        if (lancamento.isDebito()) throw new RendaException();
    }

}
