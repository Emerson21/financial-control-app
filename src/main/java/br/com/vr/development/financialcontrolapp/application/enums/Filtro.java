package br.com.vr.development.financialcontrolapp.application.enums;

import br.com.vr.development.financialcontrolapp.application.domain.model.Periodo;
import lombok.Getter;

import java.time.LocalDate;

public enum Filtro {

    SETE(7),
    QUINZE(15),
    TRINTA(30);

    @Getter
    private int dias;

    Filtro(int dias) {
        this.dias = dias;
    }

    public Periodo calcular() {
        LocalDate data = LocalDate.now();
        return new Periodo(data.minusDays(this.dias), data);
    }

}
