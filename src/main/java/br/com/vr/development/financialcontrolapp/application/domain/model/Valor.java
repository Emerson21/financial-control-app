package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@EqualsAndHashCode
public class Valor {

    @Getter
    private BigDecimal valor;

    public Valor(BigDecimal valor) {
        this.valor = valor;
    }

    public Valor(String valor) {
        this.valor = new BigDecimal(valor);
    }

    public boolean ehNegativo() {
        return this.valor.compareTo(BigDecimal.ZERO) < 0;
    }

    public Valor adiciona(Valor valor) {
        return new Valor(this.valor.add(valor.getValor()));
    }

    public Valor adiciona(BigDecimal valor) {
        return new Valor(this.valor.add(valor));
    }

    public BigDecimal asBigDecimal() {
        return this.valor;
    }

}
