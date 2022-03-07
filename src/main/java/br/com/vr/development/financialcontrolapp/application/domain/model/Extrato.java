package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

public class Extrato {

    @Getter
    private Set<Lancamento> movimentacoes;

    public Extrato(Set<Lancamento> movimentacoes, Periodo periodo) {
        this.movimentacoes = movimentacoes
                .stream()
                .filter(movimentacao ->
                    periodo.contains(movimentacao.getDataHora().toLocalDate())).collect(Collectors.toSet());
    }

}
