package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;

import java.util.*;
import java.util.stream.Collector;

public abstract class Agrupador {

    protected Agrupador() { }

    private List<Grupo> grupos = new ArrayList<>();

    protected Agrupador(Collection<? extends Movimentacao> movimentacoes,
                     Collector<Movimentacao, ?, Map<Object, List<Movimentacao>>> collector) {

        Map<Object, List<Movimentacao>> map = movimentacoes.stream().collect(collector);
        for(Map.Entry<Object, List<Movimentacao>> entry : map.entrySet()) {
            this.grupos.add(new Grupo(entry.getKey(), entry.getValue()));
        }

    }

    protected abstract String getKeyNameField();

    public List<Grupo> agruparMovimentacoes(Extrato extrato) {
        return extrato.agrupar(this);
    }

    protected abstract List<Grupo> agrupar(Collection<Movimentacao> movimentacoes);

    protected List<Grupo> getGrupos() {
        return Collections.unmodifiableList(grupos);
    }

}
