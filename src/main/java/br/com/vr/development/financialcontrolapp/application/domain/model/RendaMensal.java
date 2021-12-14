package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class RendaMensal {
    
    private BigDecimal valor;

}
