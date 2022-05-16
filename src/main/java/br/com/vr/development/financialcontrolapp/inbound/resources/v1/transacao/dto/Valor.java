package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Valor {

    private BigDecimal value;

    public br.com.vr.development.financialcontrolapp.application.domain.model.Valor toModel() {
        return new br.com.vr.development.financialcontrolapp.application.domain.model.Valor(value);
    }

}
