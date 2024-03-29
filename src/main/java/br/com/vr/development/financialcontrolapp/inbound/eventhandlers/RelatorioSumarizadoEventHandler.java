package br.com.vr.development.financialcontrolapp.inbound.eventhandlers;

import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransacaoExternaEventRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransacaoInternaEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RelatorioSumarizadoEventHandler {

    private ObjectMapper mapper;
    private TransacaoInternaEventRepository transacaoInternaEventRepository;
    private TransacaoExternaEventRepository transacaoExternaEventRepository;

    @RabbitListener(queues = {"${transacao.event.queue.name.interna}"})
    public void relatorioSumarizadoTransacaoInterna(String json) throws JsonProcessingException {
        TransacoesEventHandler transacaoMessage = mapper.readValue(json, TransacoesEventHandler.class);

        TransacaoInternaEventHandler handler = new TransacaoInternaEventHandler(transacaoMessage);
        transacaoInternaEventRepository.save(handler);
    }

    @RabbitListener(queues = {"${transacao.event.queue.name.externa}"})
    public void relatorioSumarizadoTransacaoExterna(String json) throws JsonProcessingException {
        TransacoesEventHandler transacaoMessage = mapper.readValue(json, TransacoesEventHandler.class);

        TransacaoExternaEventHandler eventHandler = new TransacaoExternaEventHandler(transacaoMessage);
        transacaoExternaEventRepository.save(eventHandler);
    }

    @RabbitListener(queues = {"${transacao.event.queue.name.reprovadas}"})
    public void relatoriosTransacaoReprovada(String json) throws JsonProcessingException {
        relatorioSumarizadoTransacaoExterna(json);
        relatorioSumarizadoTransacaoInterna(json);
    }

}
