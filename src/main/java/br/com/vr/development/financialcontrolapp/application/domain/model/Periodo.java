package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.application.enums.Competencia;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class Periodo {

    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public Periodo(Competencia competencia) {
        this.dataInicial = competencia.inicio();
        this.dataFinal = competencia.fim();
    }

    public boolean contains(LocalDateTime dataMovimentacao) {
                return dataMovimentacao != null
                && (dataInicial.atTime(LocalTime.MIN).isEqual(dataMovimentacao) || dataInicial.atTime(LocalTime.MIN).isBefore(dataMovimentacao)
                    && dataFinal.atTime(LocalTime.MAX).isEqual(dataMovimentacao) || dataFinal.atTime(LocalTime.MAX).isAfter(dataMovimentacao));
    }

}
