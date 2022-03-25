package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;

import java.time.LocalDate;

public interface Movimentacao {

    LocalDate getData();

    TipoLancamento getTipoLancamento();

    String imprimir();

}
