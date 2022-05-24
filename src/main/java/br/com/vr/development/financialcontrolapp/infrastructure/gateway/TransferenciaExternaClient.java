package br.com.vr.development.financialcontrolapp.infrastructure.gateway;

import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.infrastructure.decoder.TransferenciaExternaConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
    name = "transferenciaExternaClient",
    url = "${microservice.depositar.url}",
    configuration = TransferenciaExternaConfiguration.class
)
public interface TransferenciaExternaClient {

    @PostMapping("/deposito")
    void depositar(@RequestBody ContaDestino contaDestino);

}
