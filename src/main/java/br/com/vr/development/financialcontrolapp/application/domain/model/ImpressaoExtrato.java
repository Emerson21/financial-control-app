package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoAgrupada;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoPorDia;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class ImpressaoExtrato {

    public static final String ANSI_GREEN = "\u001B[32m";

    private Extrato extrato;

    public void imprimir(Agrupador agrupador) {

        MovimentacaoAgrupada movimentacoesAgrupadas = extrato.agrupar(agrupador);

        for(Grupo grupo : movimentacoesAgrupadas) {
            System.out.println(movimentacoesAgrupadas.lerNome());
            //grupo.getMovimentacoes().forEach(movimentacao -> System.out.println("\t"+ movimentacao.imprimir()));
            for(Movimentacao movimentacao : grupo) {
                System.out.println("\t"+ movimentacao.imprimir());
            }
        }

//        for (Grupo grupo : new MovimentacaoPorDia().getGrupos()) {
//            System.out.println(grupo);
//        }
//
//        for (Grupo grupo : agrupador.agruparMovimentacoes(extrato)) {
//            System.out.println(ANSI_GREEN + agrupador.getKeyNameField() + " : " + grupo.getAgrupador().toString() + " | Movimentações");
//            grupo.getMovimentacoes().forEach(movimentacao -> System.out.println("\t"+ movimentacao.imprimir()));
//        }

    }

}
