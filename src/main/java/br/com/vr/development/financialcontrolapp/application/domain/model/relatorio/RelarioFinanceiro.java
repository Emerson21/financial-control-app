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

        somarDespesas(despesas);
        somarRendas(receitas);

    }

    private void somarRendas(List<Receita> receitas) {
        this.totalRendas = (receitas == null || receitas.isEmpty())
                ? Valor.ZERO
                : receitas.stream().map(receita -> receita.getValor()).reduce(Valor.ZERO, Valor::adicionar);
    }

    private void somarDespesas(List<Despesa> despesas) {
        totalDespesas = (despesas == null || despesas.isEmpty())
                ? Valor.ZERO
                : despesas.stream().map(despesa -> despesa.getValor()).reduce(Valor.ZERO, Valor::adicionar);
    }
}
