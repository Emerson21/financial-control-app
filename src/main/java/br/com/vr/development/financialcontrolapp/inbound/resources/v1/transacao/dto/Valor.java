package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Valor {

    @Column(name = "valor")
    private BigDecimal value;

}
