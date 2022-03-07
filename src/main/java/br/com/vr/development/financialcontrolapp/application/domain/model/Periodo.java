package br.com.vr.development.financialcontrolapp.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Periodo {

    private LocalDate dataInicial;
    private LocalDate dataFinal;


    public boolean contains(LocalDate dataMovimentacao) {
        return dataMovimentacao != null &&
                dataInicial.isBefore(dataMovimentacao) &&
                dataFinal.isAfter(dataMovimentacao);
    }

}
