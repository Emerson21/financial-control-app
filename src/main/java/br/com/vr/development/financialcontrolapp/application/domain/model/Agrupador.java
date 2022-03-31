package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoAgrupada;

import java.util.*;
import java.util.stream.Collector;

public abstract class Agrupador {

    protected Agrupador() { }

    private Collector<Movimentacao, ?, Map<Object, List<Movimentacao>>> algoritmoDeAgrupamento;

    protected Agrupador(Collector<Movimentacao, ?, Map<Object, List<Movimentacao>>> collector) {
        this.algoritmoDeAgrupamento = collector;
    }

    protected abstract String getKeyNameField();

    public MovimentacaoAgrupada agrupar(Collection<Movimentacao> movimentacoes) {
        List<Grupo> grupos = new ArrayList<>();

        Map<Object, List<Movimentacao>> map = movimentacoes.stream().collect(this.algoritmoDeAgrupamento);
        for(Map.Entry<Object, List<Movimentacao>> entry : map.entrySet()) {
            grupos.add(new Grupo(entry.getKey(), entry.getValue()));
        }

        return new MovimentacaoAgrupada(grupos, getKeyNameField());
    }

}
