package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
public class Valor {

    @Getter
    private BigDecimal valor;

    public boolean ehNegativo() {
        return this.valor.compareTo(BigDecimal.ZERO) < 0;
    }

}
