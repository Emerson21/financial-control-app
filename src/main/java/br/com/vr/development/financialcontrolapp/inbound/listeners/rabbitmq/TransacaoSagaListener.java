package br.com.vr.development.financialcontrolapp.inbound.listeners.rabbitmq;

import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.transacoes.TransferenciaAprovada;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.transacoes.TransferenciaReprovada;
import br.com.vr.development.financialcontrolapp.exception.EventNotFoundException;
import br.com.vr.development.financialcontrolapp.inbound.listeners.TransferenciaEventDTO;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Slf4j
@ToString
@Component
@AllArgsConstructor
public class TransacaoSagaListener {

    private TransferenciaEventRepository eventRepository;
    private ObjectMapper mapper;
    private ApplicationEventPublisher eventPublisher;

    @RabbitListener(queues = {"${transacao.event.saga.transacoes.queue.aprovadas}"})
    public void recebeTransacoesAprovadas(String message) throws JsonProcessingException {
        try {
            TransferenciaEventDTO transferenciaEventDTO = parseMensagemToTransacaoEventDTO(message);
            TransferenciaEvent transferenciaEvent =
                    eventRepository.findById(transferenciaEventDTO.getCorrelationId())
                            .orElseThrow(EventNotFoundException::new);

            TransferenciaAprovada transferenciaAprovada = transferenciaEvent.toTransferenciaAprovada();
            eventRepository.save(transferenciaAprovada);

            eventPublisher.publishEvent(transferenciaAprovada);
        } catch (EventNotFoundException e) {
            e.printStackTrace();
            log.error("Erro ao consumir mensagem de transferencia aprovada {}", e);
        }
    }

    @RabbitListener(queues = {"${transacao.event.saga.transacoes.queue.reprovadas}"})
    public void recebeTransacoesReprovadas(String message) throws JsonProcessingException {
        try {
            TransferenciaEventDTO transferenciaEventDTO = parseMensagemToTransacaoEventDTO(message);
            TransferenciaEvent transferenciaEvent =
                    eventRepository.findById(transferenciaEventDTO.getCorrelationId())
                            .orElseThrow(EventNotFoundException::new);

            TransferenciaReprovada transferenciaReprovada = transferenciaEvent.toTransferenciaReprovada();
            eventRepository.save(transferenciaReprovada);

            eventPublisher.publishEvent(transferenciaReprovada);

        } catch (EventNotFoundException e) {
            e.printStackTrace();
            log.error("Erro ao consumir mensagem de transferencia reprovada {}", e);
        }
    }

    private TransferenciaEventDTO parseMensagemToTransacaoEventDTO(String message) throws JsonProcessingException {
        TransferenciaEventDTO transferenciaEventDTO = mapper.readValue(message, TransferenciaEventDTO.class);
        return transferenciaEventDTO;
    }

}
