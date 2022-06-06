package br.com.vr.development.financialcontrolapp.application.domain.service;


import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageSender {

    private RabbitTemplate rabbitTemplate;
    private TopicExchange exchange;

    public void publish(String message) {
        rabbitTemplate.convertAndSend(this.exchange.getName(), "", message);
    }

}
