package br.com.vr.development.financialcontrolapp.application.inbound;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.development.financialcontrolapp.application.inbound.dto.FormularioAberturaConta;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/conta")
public class ContaResource {
    
    @Value("${conta.abertura.valorMinimo}")
    private BigDecimal valorMinimoPermitidoParaAberturaDaConta;

    @PostMapping
    public ResponseEntity aberturaContaCorrente(@RequestBody @Valid FormularioAberturaConta formularioAberturaConta) {
        log.info("Formulario Abertura Conta {}", formularioAberturaConta);  

        if (formularioAberturaConta.isValorDepositoPermitido(valorMinimoPermitidoParaAberturaDaConta)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
