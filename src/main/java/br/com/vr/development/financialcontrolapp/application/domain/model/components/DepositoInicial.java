package br.com.vr.development.financialcontrolapp.application.domain.model.components;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositoInicial {
    
    @Getter
    private BigDecimal valor;

    public boolean ehMenorQue(BigDecimal valorMinimo) {
        return valor == null || valor.compareTo(valorMinimo) < 0;
    }

}
