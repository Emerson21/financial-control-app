package br.com.vr.development.financialcontrolapp.application.domain.model.messages;


import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ValorDTO;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransacaoMessageDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
@Embeddable
@JsonIgnoreProperties( ignoreUnknown = true )
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransacaoMessage {

    @Field(name = "correlation_id", targetType = FieldType.STRING)
    private String correlationId;

    @Field(name = "valor", targetType = FieldType.IMPLICIT)
    private ValorDTO valor;

    @Field(name = "conta_origem", targetType = FieldType.IMPLICIT)
    private ContaOrigem contaOrigem;

    @Field(name = "conta_destino", targetType = FieldType.IMPLICIT)
    private ContaDestino contaDestino;

    @Field(name = "tipo_transferencia", targetType = FieldType.STRING)
    private TipoTransferencia tipoTransferencia;

    public TransacaoMessageDTO toDto() {
        return new TransacaoMessageDTO(
                UUID.fromString(correlationId),
                new TransacaoMessageDTO.Valor(valor.getValue()),
                contaOrigem.toContaOrigemDTO(),
                contaDestino.toContaDestinoDTO(),
                tipoTransferencia.name()
        );
    }

}
