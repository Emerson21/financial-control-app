package br.com.vr.development.financialcontrolapp.application.domain.model;

import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@ToString
public class MovimentacaoPorDia implements Agrupador {

    private Map<Object, Collection> movimentacoesPorDia;

    public MovimentacaoPorDia(Collection<? extends Movimentacao> movimentacoes) {
        movimentacoesPorDia =
            new HashMap<>(
                movimentacoes.stream()
                    .collect(Collectors.groupingBy(movimentacao -> movimentacao.getDataHora().toLocalDate())));
    }

    @Override
    public Iterator<Map.Entry<Object, Collection>> iterator() {
        return movimentacoesPorDia.entrySet().iterator();
    }

    @Override
    public String getKeyNameField() {
        return "Data";
    }
}
