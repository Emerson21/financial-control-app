package br.com.vr.development.financialcontrolapp.inbound.listeners.events;

import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransacaoMessageDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferenciaReprovadaEvent extends TransferenciaEvent {

    public TransferenciaReprovadaEvent(UUID correlationId, TransacaoMessageDTO transacaoMessage) {
        super(correlationId, transacaoMessage);
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof TransferenciaReprovadaEvent) {
            return ((TransferenciaReprovadaEvent) object).correlationId().equals(this.correlationId());
        }

        return false;
    }

}
