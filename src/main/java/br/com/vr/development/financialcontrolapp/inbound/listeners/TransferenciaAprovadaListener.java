package br.com.vr.development.financialcontrolapp.inbound.listeners;

import br.com.vr.development.financialcontrolapp.application.domain.model.events.transacoes.TransferenciaAprovada;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaAprovadaEvent;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaRecebidaEvent;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaAprovadaEventRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaRecebidaEventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TransferenciaAprovadaListener {

    private TransferenciaAprovadaEventRepository eventRepository;

    @KafkaListener(
        topics = "${topico.transferencia.aprovada}",
        groupId = "${spring.kafka.group_id}",
        containerFactory = "transferenciaAprovadaContainerFactory"
    )
    public void consumir(TransferenciaAprovadaEvent evento) {
        log.info("Evento TransferenciaRecebidaEvent recebido {}", evento);

        if (!eventRepository.findByCorrelationId(evento.correlationId()).isPresent()) {
            log.info("Salvando evendo na base de dados");
            eventRepository.save(evento);
        }

        log.info("****************************************");
        log.info("****************************************");
        log.info("****************************************");
        log.info("******** Transferencia Aprovada ********");
        log.info("****************************************");
        log.info("****************************************");
        log.info("****************************************");

    }
}
