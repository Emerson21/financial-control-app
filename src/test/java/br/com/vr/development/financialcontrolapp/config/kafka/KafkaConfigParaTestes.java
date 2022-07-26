package br.com.vr.development.financialcontrolapp.config.kafka;


import br.com.vr.development.financialcontrolapp.application.domain.model.events.transacoes.TransferenciaAprovada;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class KafkaConfigParaTestes {

    @Value("${topico.transferencia.recebida}")
    private String topicoTransferenciaRecebida;

    @Value("${topico.transferencia.aprovada}")
    private String topicoTransferenciaAprovada;

    @Value("${topico.transferencia.reprovada}")
    private String topicoTransferenciaReprovada;

    @Autowired
    private KafkaConfiguration kafkaConfiguration;


    @Bean
    KafkaTemplate<String, TransferenciaSolicitadaEvent> solicitadaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaConfiguration.produtorConfig()));
    }

    @Bean
    KafkaTemplate<String, TransferenciaAprovadaEvent> aprovadaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaConfiguration.produtorConfig()));
    }

    @Bean
    KafkaTemplate<String, TransferenciaReprovadaEvent> reprovadaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaConfiguration.produtorConfig()));
    }

    @Bean
    KafkaTemplate<String, TransferenciaRecebidaEvent> recebidaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(kafkaConfiguration.produtorConfig()));
    }

    @Bean
    KafkaConsumer<String, TransferenciaRecebidaEvent> transferenciaRecebidaConsumer() {
        KafkaConsumer<String, TransferenciaRecebidaEvent> consumidorRecebida =
            new KafkaConsumer<>(kafkaConfiguration.consumerConfigs(TransferenciaRecebidaEvent.class));

        consumidorRecebida.subscribe(Collections.singletonList(topicoTransferenciaRecebida));
        return consumidorRecebida;
    }

    @Bean
    KafkaConsumer<String, TransferenciaReprovadaEvent> transferenciaReprovadaConsumer() {
        KafkaConsumer<String, TransferenciaReprovadaEvent> consumidorReprovado =
            new KafkaConsumer<>(kafkaConfiguration.consumerConfigs(TransferenciaReprovadaEvent.class));

        consumidorReprovado.subscribe(Collections.singletonList(topicoTransferenciaReprovada));
        return consumidorReprovado;
    }

    @Bean
    KafkaConsumer<String, TransferenciaAprovadaEvent> transferenciaAprovadaConsumer() {
        KafkaConsumer<String, TransferenciaAprovadaEvent> consumidorAprovada =
            new KafkaConsumer<>(kafkaConfiguration.consumerConfigs(TransferenciaAprovadaEvent.class));

        consumidorAprovada.subscribe(Collections.singletonList(topicoTransferenciaAprovada));
        return consumidorAprovada;
    }

}
