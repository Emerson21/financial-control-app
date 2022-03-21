package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoPorDia;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@ToString
public class Extrato implements Iterable<Map.Entry<Object, Collection>> {

//    private Collection<Movimentacao> movimentacoes;

    @Getter
    private Agrupador agrupador;

    public Extrato(Set<Lancamento> movimentacoes, Periodo periodo) {
//        this.movimentacoes = movimentacoes.stream()
//                .filter(lancamento -> periodo.contains(lancamento.getData()))
//                .collect(Collectors.toSet());
        this.agrupador = new MovimentacaoPorDia(movimentacoes, periodo);
    }

    public Extrato(Agrupador agrupador) {
//        this.movimentacoes = movimentacoes.stream()
//                .filter(lancamento -> periodo.contains(lancamento.getData()))
//                .collect(Collectors.toSet());

        this.agrupador = agrupador;
    }

    @Override
    public Iterator<Map.Entry<Object, Collection>> iterator() {
        return this.agrupador.iterator();
    }

}
