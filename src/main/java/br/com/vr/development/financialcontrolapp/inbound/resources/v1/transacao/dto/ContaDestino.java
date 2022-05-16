package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class ContaDestino {

    private CpfDTO cpf;

    private DadosConta dadosConta;

    public br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino toModel() {
        return null;
    }
}
