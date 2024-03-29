package br.com.vr.development.financialcontrolapp.inbound.listeners;

import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaRecebidaEvent;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaRecebidaEventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TransferenciaRecebidaListener {

    private TransferenciaRecebidaEventRepository eventRepository;

    @KafkaListener(
        topics = "${topico.transferencia.recebida}",
        groupId = "${spring.kafka.group_id}",
        containerFactory = "transferenciaRecebidaContainerFactory"
    )
    public void consumir(TransferenciaRecebidaEvent evento) {
        log.info("Evento TransferenciaRecebidaEvent recebido {}", evento);

        if (!eventRepository.findByCorrelationId(evento.correlationId()).isPresent()) {
            log.info("Salvando evendo na base de dados");
            eventRepository.save(evento);
        }

        log.info("****************************************");
        log.info("****************************************");
        log.info("****************************************");
        log.info("****************************************");
        log.info("******** Transferencia Recebida ********");
        log.info("******* Aguardando Processamento *******");
        log.info("****************************************");
        log.info("****************************************");
        log.info("****************************************");

    }
}
