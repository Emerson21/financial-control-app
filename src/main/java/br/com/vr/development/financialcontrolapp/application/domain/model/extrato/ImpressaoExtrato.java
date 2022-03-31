package br.com.vr.development.financialcontrolapp.application.domain.model.extrato;

import br.com.vr.development.financialcontrolapp.application.domain.model.Agrupador;
import br.com.vr.development.financialcontrolapp.application.domain.model.Grupo;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoAgrupada;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ImpressaoExtrato {

    public static final String ANSI_GREEN = "\u001B[32m";

    private Extrato extrato;

    public void imprimir(Agrupador agrupador) {

        MovimentacaoAgrupada movimentacoesAgrupadas = extrato.agrupar(agrupador);

        for(Grupo grupo : movimentacoesAgrupadas) {
            System.out.println(movimentacoesAgrupadas.lerNome());
            for(Movimentacao movimentacao : grupo) {
                System.out.println("\t"+ movimentacao.imprimir());
            }
        }

    }

}
