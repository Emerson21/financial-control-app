package br.com.vr.development.financialcontrolapp.application.domain.model.relatorio;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
public class RelatorioFinanceiroPorCategoria implements Relatorio {

    @Getter
    private List<TotalPorCategoria> totalPorCategorias = new ArrayList<>();

    public RelatorioFinanceiroPorCategoria(List<? extends Transacao> transacoes) {
        this.totalPorCategorias = agruparValorPorCategoria(transacoes);

    }

    private List<TotalPorCategoria> agruparValorPorCategoria(List<? extends Transacao> transacoes) {
        return transacoes.stream()
                .collect(Collectors.groupingBy(Transacao::getCategoria))
                .entrySet()
                .stream().map(entry ->
                        new TotalPorCategoria(
                                entry.getKey(),
                                entry.getValue().stream().map(Transacao::getValor).reduce(Valor::adicionar).get()
                        )
                ).collect(Collectors.toList());
    }

}
