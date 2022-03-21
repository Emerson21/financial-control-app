package br.com.vr.development.financialcontrolapp.application.domain.model.movimentacoes;

import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Movimentacao {

    LocalDate getData();

    TipoLancamento getTipoLancamento();

}
