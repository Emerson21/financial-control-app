package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Agrupador;
import lombok.ToString;


import static java.util.stream.Collectors.groupingBy;

@ToString
public class MovimentacaoPorTipoLancamento extends Agrupador {

    public MovimentacaoPorTipoLancamento() {
        super(groupingBy(Movimentacao::getTipoLancamento));
    }

    @Override
    public String getKeyNameField() {
        return "Tipo Lancamento";
    }

}
