package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ToString
public class Extrato implements Iterable<Map.Entry<LocalDate, List<Lancamento>>> {

    private MovimentacaoPorDia movimentacoes;

    public Extrato(Set<Lancamento> movimentacoes, Periodo periodo) {
        this.movimentacoes = new MovimentacaoPorDia(movimentacoes, periodo);
    }

    @Override
    public Iterator<Map.Entry<LocalDate, List<Lancamento>>> iterator() {
        return movimentacoes.iterator();
    }
}
