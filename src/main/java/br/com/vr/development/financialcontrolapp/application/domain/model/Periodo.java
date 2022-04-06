package br.com.vr.development.financialcontrolapp.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Getter
public class Periodo {

    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public boolean contains(LocalDateTime dataMovimentacao) {
        return dataMovimentacao != null &&
                dataInicial.atTime(LocalTime.MIN).isBefore(dataMovimentacao) &&
                dataFinal.atTime(LocalTime.MAX).isAfter(dataMovimentacao);
    }

}
