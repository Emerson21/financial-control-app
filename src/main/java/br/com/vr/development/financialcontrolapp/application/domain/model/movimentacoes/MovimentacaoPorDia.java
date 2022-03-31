package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Agrupador;
import lombok.ToString;

import static java.util.stream.Collectors.groupingBy;

@ToString
public class MovimentacaoPorDia extends Agrupador {

    public MovimentacaoPorDia() {
        super(groupingBy(Movimentacao::getData));
    }

    @Override
    public String getKeyNameField() {
        return "Data";
    }

}
