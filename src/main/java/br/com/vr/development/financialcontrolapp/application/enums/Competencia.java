package br.com.vr.development.financialcontrolapp.application.enums;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Arrays;

import static java.time.Month.*;

public enum Competencia {
    JANEIRO(primeiroDiaDoMes(JANUARY), ultimoDiaDoMes(JANUARY)),
    FEVEREIRO(primeiroDiaDoMes(FEBRUARY), ultimoDiaDoMes(FEBRUARY)),
    MARCO(primeiroDiaDoMes(MARCH), ultimoDiaDoMes(MARCH)),
    ABRIL(primeiroDiaDoMes(APRIL), ultimoDiaDoMes(APRIL)),
    MAIO(primeiroDiaDoMes(MAY), ultimoDiaDoMes(MAY)),
    JUNHO(primeiroDiaDoMes(JUNE), ultimoDiaDoMes(JUNE)),
    JULHO(primeiroDiaDoMes(JULY), ultimoDiaDoMes(JULY)),
    AGOSTO(primeiroDiaDoMes(AUGUST), ultimoDiaDoMes(AUGUST)),
    SETEMBRO(primeiroDiaDoMes(SEPTEMBER), ultimoDiaDoMes(SEPTEMBER)),
    OUTUBRO(primeiroDiaDoMes(OCTOBER), ultimoDiaDoMes(OCTOBER)),
    NOVEMBRO(primeiroDiaDoMes(NOVEMBER), ultimoDiaDoMes(NOVEMBER)),
    DEZEMBRO(primeiroDiaDoMes(DECEMBER), ultimoDiaDoMes(DECEMBER));

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

    public Competencia proxima() throws Exception {
        return this.getBy(this.inicio.getMonth().plus(1));
    }

    public LocalDate inicio() {
        return this.inicio;
    }

    public LocalDate fim() {
        return this.fim;
    }

    private Competencia getBy(Month month) throws Exception {
        return Arrays.stream(Competencia.values())
                .filter(mes -> mes.inicio.getMonth().equals(month))
                .findFirst()
                .orElseThrow(Exception::new);
    }

}

