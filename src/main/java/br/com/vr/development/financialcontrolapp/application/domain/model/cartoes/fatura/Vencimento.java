package br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.fatura;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Vencimento {

    private int dia;

    public static Vencimento dia(int dia) {
        return new Vencimento(dia);
    }
}
