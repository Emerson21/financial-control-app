package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.vr.development.financialcontrolapp.application.domain.model.Despesa;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import lombok.Getter;

import java.util.List;

public class RelarioFinanceiro implements Relatorio {

    private List<Despesa> despesas;
    private List<Receita> receitas;

    @Getter
    private Valor totalDespesas;

    @Getter
    private Valor totalRendas;

    public RelarioFinanceiro(List<Despesa> despesas, List<Receita> receitas) {
        this.despesas = despesas;
        this.receitas = receitas;

        this.totalDespesas = somarValores(despesas);
        this.totalRendas = somarValores(receitas);

    }

    private Valor somarValores(List<? extends Transacao> transacoes) {
        return transacoes.stream().map(Transacao::getValor).reduce(Valor.ZERO, Valor::adicionar);
    }

}
