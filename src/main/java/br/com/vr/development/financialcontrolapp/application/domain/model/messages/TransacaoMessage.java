package br.com.vr.development.financialcontrolapp.application.domain.model.messages;


import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ValorDTO;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TransacaoMessage {

    private UUID correlationId;
    private ValorDTO valor;
    private ContaOrigem contaOrigem;
    private ContaDestino contaDestino;
    private TipoTransferencia tipoTransferencia;

}
