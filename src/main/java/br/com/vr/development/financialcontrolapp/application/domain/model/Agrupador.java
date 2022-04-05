package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoAgrupada;

import java.util.*;
import java.util.stream.Collector;

public abstract class Agrupador {

    private Collector<Movimentacao, ?, Map<Object, List<Movimentacao>>> algoritmoDeAgrupamento;

    protected abstract String getKeyNameField();

    protected Agrupador(Collector<Movimentacao, ?, Map<Object, List<Movimentacao>>> collector) {
        this.algoritmoDeAgrupamento = collector;
    }

    public List<MovimentacaoAgrupada> agrupar(Collection<Movimentacao> movimentacoes) {
        List<MovimentacaoAgrupada> movimentacaoAgrupadas = new ArrayList<>();

        Map<Object, List<Movimentacao>> agrupamento = movimentacoes.stream().collect(this.algoritmoDeAgrupamento);

        agrupamento.entrySet().stream().map(entry ->
                new MovimentacaoAgrupada(
                        new Grupo(entry.getValue()),
                        getKeyNameField(),
                        entry.getKey().toString()
                )
            );

        for(Map.Entry<Object, List<Movimentacao>> entry : agrupamento.entrySet()) {
            movimentacaoAgrupadas.add(new MovimentacaoAgrupada(new Grupo(entry.getValue()), getKeyNameField(), entry.getKey().toString()));
        }

        return movimentacaoAgrupadas;
    }

}
