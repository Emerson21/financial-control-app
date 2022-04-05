package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.vr.development.financialcontrolapp.application.domain.model.Despesa;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import lombok.Getter;

import java.util.List;

public class RelatorioFinanceiro implements Relatorio {

    @Getter
    private Valor totalDespesas;

    @Getter
    private Valor totalRendas;

    public RelatorioFinanceiro(List<Despesa> despesas, List<Receita> receitas) {
        this.totalDespesas = somarValores(despesas);
        this.totalRendas = somarValores(receitas);
    }

    private Valor somarValores(List<? extends Transacao> transacoes) {
        return transacoes.stream().map(Transacao::getValor).reduce(Valor.ZERO, Valor::adicionar);
    }

}
