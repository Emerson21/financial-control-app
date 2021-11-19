package br.com.vr.development.financialcontrolapp.application.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Renda implements Serializable {
    
    private BigDecimal valor;

}
