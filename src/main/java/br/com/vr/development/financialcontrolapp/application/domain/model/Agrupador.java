package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;

import java.util.*;
import java.util.stream.Collector;

public abstract class Agrupador implements Iterable<Map.Entry<Object, Collection>> {

    private Map<Object, Collection> movimentacoes;

    protected Agrupador(Collection<? extends Movimentacao> movimentacoes, Periodo periodo,
                     Collector<Movimentacao, ?, Map<Object, List<Movimentacao>>> collector) {

        this.movimentacoes = new HashMap<>(movimentacoes.stream()
                .filter(lancamento -> periodo.contains(lancamento.getData())).collect(collector)
        );
    }

    protected abstract String getKeyNameField();

    @Override
    public Iterator<Map.Entry<Object, Collection>> iterator() {
        return movimentacoes.entrySet().iterator();
    }

}
