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
public class DadosConta {

    @Column
    private String banco;

    @Column
    private String agencia;

    @Column
    private String digitoAgencia;

    @Column
    private String numeroConta;

    @Column
    private String digitoConta;

}
