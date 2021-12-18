package br.com.vr.development.financialcontrolapp.application.domain.model.components;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Definido o construtor padrao como private por conta do hibernate.
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DepositoInicial {
    
    private BigDecimal valor;

}
