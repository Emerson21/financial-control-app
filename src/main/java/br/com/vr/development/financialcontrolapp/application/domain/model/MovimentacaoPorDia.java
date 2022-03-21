package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
public class MovimentacaoPorDia implements Movimentacao, Iterable<Map.Entry<LocalDate, List<Lancamento>>> {

    private Map<LocalDate, List<Lancamento>> movimentacoesPorDia;

    public MovimentacaoPorDia(Set<Lancamento> lancamentos, Periodo periodo) {
        movimentacoesPorDia = lancamentos.stream()
                .filter(lancamento -> periodo.contains(lancamento.getDataHora().toLocalDate()))
                .collect(Collectors.groupingBy(lancamento -> lancamento.getDataHora().toLocalDate()));
    }

    @Override
    public Iterator<Map.Entry<LocalDate, List<Lancamento>>> iterator() {
        return movimentacoesPorDia.entrySet().iterator();
    }
}
