package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Agrupador;
import br.com.vr.development.financialcontrolapp.application.domain.model.Grupo;
import lombok.ToString;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@ToString
public class MovimentacaoPorDia extends Agrupador {

    public MovimentacaoPorDia() {}

    private MovimentacaoPorDia(Collection<Movimentacao> movimentacoes) {
        super(movimentacoes, groupingBy(Movimentacao::getData));
    }

    @Override
    public String getKeyNameField() {
        return "Data";
    }

    @Override
    protected List<Grupo> agrupar(Collection<Movimentacao> movimentacoes) {
        return new MovimentacaoPorDia(movimentacoes).getGrupos();
    }
}
