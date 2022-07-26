package br.com.vr.development.financialcontrolapp.inbound.listeners;

import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaReprovadaEvent;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaReprovadaEventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TransferenciaReprovadaListener {

    private TransferenciaReprovadaEventRepository eventRepository;

    @KafkaListener(
        topics = "${topico.transferencia.reprovada}",
        groupId = "${spring.kafka.group_id}",
        containerFactory = "transferenciaReprovadaContainerFactory"
    )
    public void consumir(TransferenciaReprovadaEvent evento) {
        log.info("Evento TransferenciaRecebidaEvent reprovada {}", evento);

        if (!eventRepository.findByCorrelationId(evento.correlationId()).isPresent()) {
            log.info("Salvando evendo na base de dados");
            eventRepository.save(evento);
        }

        log.info("****************************************");
        log.info("****************************************");
        log.info("****************************************");
        log.info("******** Transferencia Reprovada ********");
        log.info("****************************************");
        log.info("****************************************");
        log.info("****************************************");

    }
}
