package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@ToString
public class Extrato implements Iterable<Map.Entry<Object, Collection>> {

    private Collection<Movimentacao> movimentacoes;

    @Getter
    private Agrupador agrupador;

    public Extrato(Set<Lancamento> movimentacoes, Periodo periodo) {
        this.movimentacoes = movimentacoes.stream()
                .filter(lancamento -> periodo.contains(lancamento.getDataHora().toLocalDate()))
                .collect(Collectors.toSet());
        this.agrupador = new MovimentacaoPorDia(this.movimentacoes);
    }

    @Override
    public Iterator<Map.Entry<Object, Collection>> iterator() {
        return this.agrupador.iterator();
    }

}
