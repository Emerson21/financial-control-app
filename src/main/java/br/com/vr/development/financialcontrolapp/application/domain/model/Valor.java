package br.com.vr.development.financialcontrolapp.application.domain.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.text.NumberFormat;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Valor implements Comparable<Valor> {

    public static final Valor ZERO = new Valor("0");

    private BigDecimal valor;

    public Valor(BigDecimal valor) {
        this.valor = valor;
    }

    public Valor(String valor) {
        this.valor = new BigDecimal(valor);
    }

    public static Valor de(String valor) {
        return new Valor(valor);
    }

    public boolean ehNegativo() {
        return this.valor.compareTo(BigDecimal.ZERO) < 0;
    }

    public Valor mais(Valor valor) {
        return new Valor(this.valor.add(valor.getValor()));
    }

    public Valor mais(BigDecimal valor) {
        return new Valor(this.valor.add(valor));
    }

    private BigDecimal getValor() {
        return this.valor;
    }

    public Valor menos(Valor valor) {
        return new Valor(this.valor.subtract(valor.getValor()));
    }

    @Override
    public int compareTo(Valor valor) {
        if ( valor == null )
            throw new NullPointerException();
        
        return this.valor.compareTo(valor.getValor());
    }

    public Valor negate() {
        return new Valor(this.valor.negate());
    }

    public Valor abs() {
        return new Valor(this.valor.abs());
    }

    public String toString() {
        if (this.ehNegativo()) {
            return String.format("Valor: %s", NumberFormat.getCurrencyInstance().format(this.valor));
        }

        return String.format("Valor: %s", NumberFormat.getCurrencyInstance().format(this.valor));
    }

    @Override
    public boolean equals(Object valor) {
        if ( valor == null )
            throw new NullPointerException();

        if (!(valor instanceof Valor)) {
            throw new IllegalArgumentException();
        }

        return this.valor.compareTo(((Valor) valor).getValor()) == 0;
    }
}
