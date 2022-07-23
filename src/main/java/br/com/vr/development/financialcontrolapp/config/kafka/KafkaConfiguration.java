package br.com.vr.development.financialcontrolapp.config.kafka;

import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaRecebidaEvent;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaSolicitadaEvent;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaSucessoEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

@Configuration
public class KafkaConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServerUrl;

    @Value("${spring.kafka.group_id}")
    private String groupId;

    @Value("${spring.kafka.auto-offset-reset}")
    private String autoOffSetReset;


    @Bean
    KafkaTemplate<String, TransferenciaSolicitadaEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    ProducerFactory<String, TransferenciaSolicitadaEvent> producerFactory() {
        Map<String, Object> props = new HashMap<>() {{
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerUrl);
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        }};

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, TransferenciaRecebidaEvent>> transferenciaSolicitada() {
        ConcurrentKafkaListenerContainerFactory<String, TransferenciaRecebidaEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<String, TransferenciaRecebidaEvent>();
        factory.setConsumerFactory(transferenciaRecebidaConsumerFactory());
        return factory;
    }

    @Bean
    ConsumerFactory<String, TransferenciaRecebidaEvent> transferenciaRecebidaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
          consumerConfigs(TransferenciaRecebidaEvent.class),
          new StringDeserializer(),
          jsonDeserializer(TransferenciaRecebidaEvent.class));
    }

    @Bean
    ConsumerFactory<String, TransferenciaSucessoEvent> transferenciaSucessoConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(TransferenciaSucessoEvent.class),
                new StringDeserializer(),
                jsonDeserializer(TransferenciaSucessoEvent.class));
    }

    private <T> JsonDeserializer<T> jsonDeserializer(Class<T> clazz){
        return new JsonDeserializer<T>(clazz, false);
    }

    public Map<String, Object> consumerConfigs(Class<?> clazz) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffSetReset);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerUrl);

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        props.put(JsonDeserializer.TYPE_MAPPINGS, typeMappings(clazz));

        return props;
    }

    private String typeMappings(Class<?> ...classes) {
        return stream(classes)
                .map(clazz -> clazz.getSimpleName().toLowerCase() + ":" + clazz.getName())
                .collect(joining(","));
    }


}
