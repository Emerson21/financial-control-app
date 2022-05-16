package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DadosConta {

    private String banco;

    private String agencia;
    private String digitoAgencia;

    private String numeroConta;
    private String digitoConta;

}
