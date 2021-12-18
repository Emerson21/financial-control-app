package br.com.vr.development.financialcontrolapp.application.domain.model.components;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Setting defaut constructor as access private for specific hibernate use.
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DepositoInicial {
    
    private BigDecimal valor;

}
