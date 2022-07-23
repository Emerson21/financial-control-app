package br.com.vr.development.financialcontrolapp.application.domain.model.events.listeners;

import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.transacoes.TransferenciaReprovada;
import br.com.vr.development.financialcontrolapp.application.domain.service.MessageSender;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class TransferenciasEventsListener {

    private TransferenciaEventRepository eventRepository;
    private MessageSender messageSender;
    private ObjectMapper mapper;

    @Qualifier("transferenciaEventExchange")
    private TopicExchange transferenciaEventExchange;

    @EventListener
    @Transactional
    public void transferenciaInternaEventListener(TransferenciaEvent transferenciaEvent) throws JsonProcessingException {
        log.info("Recebendo evento de dominio {}", transferenciaEvent);
        eventRepository.save(transferenciaEvent);
        String routingKey = transferenciaEvent.isInterna() ? "interna" : "externa";
        messageSender.publish(mapper.writeValueAsString(transferenciaEvent), transferenciaEventExchange, routingKey);
    }

    @EventListener
    public void transferenciaReprovadaEventListener(TransferenciaReprovada transferenciaReprovada) throws JsonProcessingException {
        log.info("Recebendo evento de dominio {}", transferenciaReprovada);
        eventRepository.save(transferenciaReprovada);
        messageSender.publish(mapper.writeValueAsString(transferenciaReprovada), transferenciaEventExchange, "transferenciaReprovada");
    }
}
