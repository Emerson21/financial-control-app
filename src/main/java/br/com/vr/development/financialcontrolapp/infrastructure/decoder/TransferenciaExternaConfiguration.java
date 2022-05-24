package br.com.vr.development.financialcontrolapp.infrastructure.decoder;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TransferenciaExternaConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder().errorDecoder(new TransferenciaExternaErrorDecode());
    }
}
