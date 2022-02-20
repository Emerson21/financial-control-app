package br.com.vr.development.financialcontrolapp.application.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Valor implements Comparable {

    public static final Valor ZERO = new Valor("0");

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

    public Valor adicionar(Valor valor) {
        return new Valor(this.valor.add(valor.getValor()));
    }

    public Valor adicionar(BigDecimal valor) {
        return new Valor(this.valor.add(valor));
    }

    private BigDecimal getValor() {
        return this.valor;
    }

    @Override
    public int compareTo(Object valor) {
        if ( valor == null )
            throw new NullPointerException();
        
        if (!(valor instanceof Valor)) 
            throw new IllegalArgumentException();
       
        return this.valor.compareTo(((Valor) valor).getValor());
    }

    public Valor negate() {
        return new Valor(this.valor.negate());
    }

    public Valor abs() {
        return new Valor(this.valor.abs());
    }

}
