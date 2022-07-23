package br.com.vr.development.financialcontrolapp.inbound.listeners.events;

import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;

import java.util.UUID;

public class TransferenciaRecebidaEvent extends TransferenciaEvent {

    public TransferenciaRecebidaEvent(UUID correlationId, TransacaoMessage transacaoMessage) {
        super(correlationId, transacaoMessage);
    }

}
