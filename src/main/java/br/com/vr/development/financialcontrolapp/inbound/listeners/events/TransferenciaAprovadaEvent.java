package br.com.vr.development.financialcontrolapp.inbound.listeners.events;

import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferenciaAprovadaEvent extends TransferenciaEvent {

    public TransferenciaAprovadaEvent(UUID correlationId, TransacaoMessage transacaoMessage) {
        super(correlationId, transacaoMessage);
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof TransferenciaAprovadaEvent) {
            return ((TransferenciaAprovadaEvent) object).correlationId().equals(this.correlationId());
        }

        return false;
    }

}
