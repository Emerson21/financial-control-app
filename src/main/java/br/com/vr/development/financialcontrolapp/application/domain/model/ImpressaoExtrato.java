package br.com.vr.development.financialcontrolapp.application.domain.model;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;

@AllArgsConstructor
public class ImpressaoExtrato {

    public static final String ANSI_GREEN = "\u001B[32m";

    private Extrato extrato;

    public void imprimir() {

        for (Map.Entry<Object, Collection> entry : extrato) {
            System.out.println(ANSI_GREEN + extrato.getAgrupador().getKeyNameField() + " : " + entry.getKey().toString() + " | Movimentações");

            entry.getValue().forEach(movimentacao -> {
                System.out.println("\t"+ movimentacao);
            });

        }

    }

}
