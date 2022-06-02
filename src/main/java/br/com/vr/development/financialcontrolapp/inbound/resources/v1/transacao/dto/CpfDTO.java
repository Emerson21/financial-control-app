package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class CpfDTO {

    @Column(name = "cpf_conta_destino")
    private String value;

}
