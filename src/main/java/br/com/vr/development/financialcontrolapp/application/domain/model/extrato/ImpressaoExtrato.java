package br.com.vr.development.financialcontrolapp.application.domain.model.extrato;

import br.com.vr.development.financialcontrolapp.application.domain.model.Agrupador;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.Movimentacao;
import br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes.MovimentacaoAgrupada;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ImpressaoExtrato {

    private Extrato extrato;
    private SaidaExtrato saidaExtrato;

    public void imprimir(Agrupador agrupador) {

        List<MovimentacaoAgrupada> movimentacoesAgrupadas = extrato.agrupar(agrupador);

        movimentacoesAgrupadas.forEach(movimentacaoAgrupada -> {
            String nome = movimentacaoAgrupada.lerNome();
            saidaExtrato.imprimir(nome);

            for(Movimentacao movimentacao : movimentacaoAgrupada.getGrupo()) {
                saidaExtrato.imprimir("\t"+ movimentacao.imprimir());
            }
        });
    }
}
