package br.com.vr.development.financialcontrolapp.application.domain.service;


import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaSolicitadaEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange exchange;

    @Autowired
    private KafkaTemplate<String, TransferenciaSolicitadaEvent> kafkaTemplate;


    @Value("${topico.transferencia.solicitada}")
    private String transferenciaSolicitada;

    public void publish(String message) {
        this.publish(message, exchange, "");
    }

    public void publish(String message, TopicExchange topicExchange, String routingKey) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, message);
    }

    public void publishKafka(TransferenciaSolicitadaEvent event) {
        kafkaTemplate.send(transferenciaSolicitada, event.correlationId(), event);
    }

}
