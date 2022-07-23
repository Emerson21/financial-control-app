package br.com.vr.development.financialcontrolapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("eventosTransacoes")
public class EventTransacoesRabbitMQConfig {

    @Value("${transacao.event.exchange.name}")
    private String transferenciaEventExchange;



    @Value("${transacao.event.exchange.wait}")
    private String transferenciaEventExchangeWait;

    @Value("${transacao.event.exchange.error}")
    private String transferenciaEventExchangeError;

    @Value("${transacao.event.queue.name.interna}")
    private String transferenciaInternaEventQueue;

    @Value("${transacao.event.queue.name.externa}")
    private String transferenciaExternaEventQueue;


    @Value("${transacao.event.queue.wait}")
    private String transferenciaEventQueueWait;

    @Value("${transacao.event.queue.error}")
    private String transferenciaEventQueueError;


    @Value("${transacao.event.queue.name.reprovadas}")
    private String transferenciaReprovadaEventQueue;


    @Bean
    public TopicExchange transferenciaEventExchange() {
        return new TopicExchange(transferenciaEventExchange, true, false);
    }



    @Bean
    public TopicExchange transferenciaEventExchangeWait() {
        return new TopicExchange(transferenciaEventExchangeWait, true, false);
    }

    @Bean
    public TopicExchange transferenciaEventExchangeError() {
        return new TopicExchange(transferenciaEventExchangeError, true, false);
    }

    @Bean
    public Queue transferenciaInternaEventQueue() {
        return QueueBuilder.durable(transferenciaInternaEventQueue)
                .withArgument("x-dead-letter-exchange", transferenciaEventExchangeWait)
                .withArgument("durable", true)
                .build();
    }

    @Bean
    public Queue transferenciaExternaEventQueue() {
        return QueueBuilder.durable(transferenciaExternaEventQueue)
                .withArgument("x-dead-letter-exchange", transferenciaEventExchangeWait)
                .withArgument("durable", true)
                .build();
    }

    @Bean
    public Queue transferenciaEventQueueWait() {
        return QueueBuilder.durable(transferenciaEventQueueWait)
                .withArgument("x-dead-letter-exchange", transferenciaEventExchangeError)
                .withArgument("durable", true)
                .build();
    }

    @Bean
    public Queue transferenciaEventQueueError() {
        return QueueBuilder.durable(transferenciaEventQueueError)
                .withArgument("durable", true)
                .build();
    }

    @Bean
    public Queue transferenciaReprovadaEventQueue() {
        return QueueBuilder.durable(transferenciaReprovadaEventQueue)
                .withArgument("x-dead-letter-exchange", transferenciaEventExchangeWait)
                .withArgument("durable", true)
                .build();
    }


    @Bean
    public Binding bindingTransferenciaInternaEventQueue() {
        return BindingBuilder.bind(transferenciaInternaEventQueue()).to(transferenciaEventExchange()).with("interna");
    }

    @Bean
    public Binding bindingTransferenciaExternaEventQueue() {
        return BindingBuilder.bind(transferenciaExternaEventQueue()).to(transferenciaEventExchange()).with("externa");
    }

    @Bean
    public Binding bindingTransferenciaEventQueueWait() {
        return BindingBuilder.bind(transferenciaEventQueueWait()).to(transferenciaEventExchangeWait()).with("");
    }

    @Bean
    public Binding bindingTransferenciaEventQueueError() {
        return BindingBuilder.bind(transferenciaEventQueueError()).to(transferenciaEventExchangeError()).with("");
    }


    @Bean
    public Binding bindingTransferenciaReprovadasEventQueue() {
        return BindingBuilder.bind(transferenciaReprovadaEventQueue()).to(transferenciaEventExchange()).with("transferenciaReprovada");
    }

    @Bean
    MessageConverter converterTransacoesEvents() {
        return new Jackson2JsonMessageConverter();
    }

}
