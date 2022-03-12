package br.com.vr.development.financialcontrolapp.application.domain.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImpressaoExtrato {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m\u001B[1m";

    private Extrato extrato;

    public void imprimir() {

        extrato.getMovimentacoes().getMovimentacoesPorDia().forEach((data, movimentacoes) -> {
            System.out.println(ANSI_YELLOW + "Data: " + data.toString() + " | Movimentações");
            movimentacoes.forEach(movimentacao -> {
                String linha = movimentacao.getValor().ehNegativo()
                        ? ANSI_RED + movimentacao
                        : ANSI_GREEN + movimentacao;

                System.out.println("\t"+ linha);
            });
        });

    }

}
