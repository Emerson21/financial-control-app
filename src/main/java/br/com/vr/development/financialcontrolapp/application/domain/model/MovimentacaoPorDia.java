package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MovimentacaoPorDia implements Movimentacao {

    @Getter
    private Map<LocalDate, List<Lancamento>> movimentacoesPorDia;

    public MovimentacaoPorDia(Set<Lancamento> lancamentos, Periodo periodo) {
        movimentacoesPorDia = lancamentos.stream()
                .filter(lancamento -> periodo.contains(lancamento.getDataHora().toLocalDate()))
                .collect(Collectors.groupingBy(lancamento -> lancamento.getDataHora().toLocalDate()));
    }

}
