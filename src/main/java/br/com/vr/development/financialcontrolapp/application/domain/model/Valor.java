package br.com.vr.development.financialcontrolapp.application.domain.model;

import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ValorDTO;
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
        if (valor == null) {
            throw new NullPointerException();
        }

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
        return new Valor(this.valor.add(valor.valor));
    }

    public Valor mais(BigDecimal valor) {
        return new Valor(this.valor.add(valor));
    }

    public Valor menos(Valor valor) {
        return new Valor(this.valor.subtract(valor.valor));
    }

    @Override
    public int compareTo(Valor valor) {
        return this.valor.compareTo(valor.valor);
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

        return this.valor.compareTo(((Valor) valor).valor) == 0;
    }

    public ValorDTO toValorDTO() {
        return new ValorDTO(valor);
    }
}
