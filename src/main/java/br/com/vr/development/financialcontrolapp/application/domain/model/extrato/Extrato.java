package br.com.vr.development.financialcontrolapp.application.domain.model.extrato;

import br.com.vr.development.financialcontrolapp.application.domain.model.Agrupador;
import br.com.vr.development.financialcontrolapp.application.domain.model.Periodo;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoAgrupada;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@ToString
public class Extrato  {

    private Collection<Movimentacao> movimentacoes;

    public Extrato(Set<Lancamento> movimentacoes, Periodo periodo) {
        this.movimentacoes = movimentacoes.stream()
                .filter(lancamento -> periodo.contains(lancamento.getDataHora()))
                .collect(Collectors.toSet());
    }

    public List<MovimentacaoAgrupada> agrupar(Agrupador agrupador) {
        return agrupador.agrupar(movimentacoes);
    }
}
