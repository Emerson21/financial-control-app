package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura.Vencimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import br.com.vr.development.financialcontrolapp.application.enums.StatusFatura;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fatura {

    private final Periodo periodo;
    private final Vencimento vencimento;
    private List<Lancamento> lancamentos;
    private Valor valor = Valor.ZERO;
    private StatusFatura status = StatusFatura.EM_ABERTO;

    public Fatura(Competencia competencia, Vencimento vencimento) {
        this.periodo = new Periodo(competencia);
        this.vencimento = vencimento;
    }

    public void novoLancamento(Valor valor, Descricao descricao) {
        if (lancamentos == null) lancamentos = new ArrayList<>();
        lancamentos.add(
            Lancamento.criaLancamentoNegativo(valor, descricao, null, TipoTransferencia.CARTAO_CREDITO)
        );

        this.valor = this.valor.mais(valor);
    }

    public List<Lancamento> lancamentos() {
        return Collections.unmodifiableList(lancamentos);
    }

    public Valor valor() {
        return this.valor;
    }

    public boolean ehDa(Competencia competencia) {
        return this.periodo.contains(competencia.inicio().atStartOfDay());
    }

    public StatusFatura status() {
        return this.status;
    }

    public void pagar(Valor valorPagamento) {
        this.status = StatusFatura.definir(valorPagamento, this.valor);
    }
}
