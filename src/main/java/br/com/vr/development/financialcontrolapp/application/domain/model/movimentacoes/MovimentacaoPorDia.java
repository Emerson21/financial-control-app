package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Agrupador;
import br.com.vr.development.financialcontrolapp.application.domain.model.Periodo;
import lombok.ToString;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@ToString
public class MovimentacaoPorDia extends Agrupador {

    public MovimentacaoPorDia(Collection<? extends Movimentacao> movimentacoes, Periodo periodo) {
        super(movimentacoes, periodo, groupingBy(Movimentacao::getData));
    }

    @Override
    public String getKeyNameField() {
        return "Data";
    }
}
