package br.com.vr.development.financialcontrolapp.application.enums;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

import static java.time.Month.*;

public enum Competencia {
    JANEIRO(primeiroDiaDoMes(JANUARY), ultimoDiaDoMes(JANUARY)),
    FEVEREIRO(primeiroDiaDoMes(FEBRUARY), ultimoDiaDoMes(FEBRUARY)),
    MARCO(primeiroDiaDoMes(MARCH), ultimoDiaDoMes(MARCH));

    private static LocalDate primeiroDiaDoMes(Month month) {
        return YearMonth.of(LocalDate.now().getYear(), month).atDay(1);
    }

    private static LocalDate ultimoDiaDoMes(Month month) {
        return YearMonth.of(LocalDate.now().getYear(), month).atEndOfMonth();
    }

    private LocalDate inicio;
    private LocalDate fim;

    Competencia(LocalDate inicio, LocalDate fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public LocalDate inicio() {
        return this.inicio;
    }

    public LocalDate fim() {
        return this.fim;
    }
}

