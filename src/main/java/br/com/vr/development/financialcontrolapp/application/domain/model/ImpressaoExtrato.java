package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoPorDia;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ImpressaoExtrato {

    public static final String ANSI_GREEN = "\u001B[32m";

    private Extrato extrato;

    public void imprimir(Agrupador agrupador) {

        for (Grupo grupo : new MovimentacaoPorDia().getGrupos()) {
            System.out.println(grupo);
        }

        for (Grupo grupo : agrupador.agruparMovimentacoes(extrato)) {
            System.out.println(ANSI_GREEN + agrupador.getKeyNameField() + " : " + grupo.getAgrupador().toString() + " | Movimentações");
            grupo.getMovimentacoes().forEach(movimentacao -> System.out.println("\t"+ movimentacao.imprimir()));
        }

    }

}
