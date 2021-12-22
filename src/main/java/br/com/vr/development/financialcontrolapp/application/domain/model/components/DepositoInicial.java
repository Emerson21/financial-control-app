package br.com.vr.development.financialcontrolapp.application.domain.model.components;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DepositoInicial {
    
    private BigDecimal valor;

}
