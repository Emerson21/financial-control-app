package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Agrupador;
import br.com.vr.development.financialcontrolapp.application.domain.model.Grupo;
import lombok.ToString;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@ToString
public class MovimentacaoPorTipoLancamento extends Agrupador {

    public MovimentacaoPorTipoLancamento() {}

    private MovimentacaoPorTipoLancamento(Collection<Movimentacao> movimentacoes) {
        super(movimentacoes, groupingBy(Movimentacao::getTipoLancamento));
    }

    @Override
    public String getKeyNameField() {
        return "Tipo Lancamento";
    }

    @Override
    protected List<Grupo> agrupar(Collection<Movimentacao> movimentacoes) {
        return new MovimentacaoPorTipoLancamento(movimentacoes).getGrupos();
    }

}
