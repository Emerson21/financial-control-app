package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fatura {

    private Periodo periodo;

    private Vencimento vencimento;

    private List<Lancamento> lancamentos;

    public Fatura(Competencia competencia, Vencimento vencimento) {
        this.periodo = new Periodo(competencia.inicio(), competencia.fim());
        this.vencimento = vencimento;
    }

    public void novoLancamento(Valor valor, Descricao descricao) {
        if (lancamentos == null) lancamentos = new ArrayList<>();

        lancamentos.add(
            Lancamento.criaLancamentoNegativo(valor, descricao, null, TipoTransferencia.CARTAO_CREDITO)
        );
    }

    public List<Lancamento> lancamentos() {
        return Collections.unmodifiableList(lancamentos);
    }
}
