package br.com.vr.development.financialcontrolapp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransacoesSagaConfig {

    @Value("${transacao.event.saga.transacoes.exchange.aprovadas}")
    private String transacoesAprovadas;

    @Value("${transacao.event.saga.transacoes.exchange.reprovadas}")
    private String transacoesReprovadas;


    @Value("${transacao.event.saga.transacoes.queue.aprovadas}")
    private String queueNameTransacoesAprovadas;

    @Value("${transacao.event.saga.transacoes.queue.reprovadas}")
    private String queueNameTransacoesReprovadas;

    @Bean
    public TopicExchange transacoesAprovadas() {
        return new TopicExchange(transacoesAprovadas, true, false);
    }

    @Bean
    public TopicExchange transacoesReprovadas() {
        return new TopicExchange(transacoesReprovadas, true, false);
    }

    @Bean
    public Queue queueTransacoesAprovadas() {
        return QueueBuilder.durable(queueNameTransacoesAprovadas).build();
    }

    @Bean
    public Queue queueTransacoesReprovadas() {
        return QueueBuilder.durable(queueNameTransacoesReprovadas).build();
    }

    @Bean
    public Binding bindingQueueTransacoesAprovadas() {
        return BindingBuilder.bind(queueTransacoesAprovadas()).to(transacoesAprovadas()).with("");
    }

    @Bean
    public Binding bindingQueueTransacoesReprovadas() {
        return BindingBuilder.bind(queueTransacoesReprovadas()).to(transacoesReprovadas()).with("");
    }

    @Bean
    MessageConverter transacoesSagaConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
