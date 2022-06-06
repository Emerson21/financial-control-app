package br.com.vr.development.financialcontrolapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${transacao.exchange.name}")
    private String exchangeName;

    @Value("${transacao.exchange.wait}")
    private String exchangeWait;

    @Value("${transacao.exchange.error}")
    private String exchangeError;

    @Value("${transacao.queue.name}")
    private String queue;

    @Value("${transacao.queue.wait}")
    private String queueWait;

    @Value("${transacao.queue.error}")
    private String queueError;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName, true, false);
    }

    @Bean
    public TopicExchange exchangeWait() {
        return new TopicExchange(exchangeWait, true, false);
    }

    @Bean
    public TopicExchange exchangeError() {
        return new TopicExchange(exchangeError, true, false);
    }


    @Bean
    public Queue queue() {
        return QueueBuilder.durable(queue)
                .withArgument("x-dead-letter-exchange", exchangeWait)
                .withArgument("durable", true)
                .build();
    }

    @Bean
    public Queue queueWait() {
        return QueueBuilder.durable(queueWait)
                .withArgument("x-dead-letter-exchange", exchangeError)
                .withArgument("durable", true)
                .build();
    }

    @Bean
    public Queue queueError() {
        return QueueBuilder.durable(queueError)
                .withArgument("durable", true)
                .build();
    }

    @Bean
    public Binding bindingQueue() {
        return BindingBuilder.bind(queue()).to(exchange()).with("");
    }

    @Bean
    public Binding bindingQueueWait() {
        return BindingBuilder.bind(queueWait()).to(exchangeWait()).with("");
    }

    @Bean
    public Binding bindingQueueError() {
        return BindingBuilder.bind(queueError()).to(exchangeError()).with("");
    }

    @Bean
    MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
