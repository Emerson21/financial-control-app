package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ImpressaoExtrato {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m\u001B[1m";

    private Extrato extrato;

    public void imprimir() {

        for(Map.Entry<LocalDate, List<Lancamento>> entry : extrato) {
            System.out.println(ANSI_YELLOW + "Data: " + entry.getKey().toString() + " | Movimentações");

            entry.getValue().forEach(movimentacao -> {
                String linha = movimentacao.getValor().ehNegativo()
                        ? String.format(ANSI_GREEN + "%s | " + ANSI_RED + " %s " + ANSI_GREEN + " | %s ",
                        movimentacao.getTipoTransferencia(), movimentacao.getValor(), movimentacao.getDescricao())
                        : String.format(ANSI_GREEN + "%s |  %s  | %s ",
                        movimentacao.getTipoTransferencia(), movimentacao.getValor(), movimentacao.getDescricao());

                System.out.println("\t"+ linha);
            });
        }

    }

}
