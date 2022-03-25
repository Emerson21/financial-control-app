package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@ToString
public class Extrato  {

    private Collection<Movimentacao> movimentacoes;

    public Extrato(Set<Lancamento> movimentacoes, Periodo periodo) {
        this.movimentacoes = movimentacoes.stream()
                .filter(lancamento -> periodo.contains(lancamento.getData()))
                .collect(Collectors.toSet());
    }

    public List<Grupo> agrupar(Agrupador agrupador) {
        return agrupador.agrupar(movimentacoes);
    }
}
