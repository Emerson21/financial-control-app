package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class Transacao {

    private String cpf;

    private Valor valor;

    private TipoTransferencia tipo;

    private ContaDestino conta;


}
