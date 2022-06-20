package br.com.vr.development.financialcontrolapp.inbound.eventhandlers;

import br.com.vr.development.financialcontrolapp.infrastructure.repository.ESRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RelatorioSumarizadoEventHandler {

    private ObjectMapper mapper;
    private ESRepository esRepository;

    @RabbitListener(queues = {"${transacao.event.queue.name.interna}"})
    public void relatorioSumarizadoTransacaoInterna(String json) throws JsonProcessingException {
        TransacoesEventHandler transacaoMessage = mapper.readValue(json, TransacoesEventHandler.class);

        TransacaoInternaEventHandler handler = new TransacaoInternaEventHandler(transacaoMessage);
        esRepository.save(handler);
    }

    @RabbitListener(queues = {"${transacao.event.queue.name.externa}"})
    public void relatorioSumarizadoTransacaoExterna(String json) throws JsonProcessingException {
        TransacoesEventHandler transacaoMessage = mapper.readValue(json, TransacoesEventHandler.class);

        TransacaoExternaEventHandler eventHandler = new TransacaoExternaEventHandler(transacaoMessage);
        esRepository.save(eventHandler);
    }


}
