package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Agrupador;
import br.com.vr.development.financialcontrolapp.application.domain.model.Periodo;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@ToString
public class MovimentacaoPorTipoLancamento extends Agrupador {

    public MovimentacaoPorTipoLancamento(Collection<? extends Movimentacao> movimentacoes, Periodo periodo) {
        super(movimentacoes, periodo, Collectors.groupingBy(Movimentacao::getTipoLancamento));
    }

    @Override
    public String getKeyNameField() {
        return "Tipo Lancamento";
    }

}
