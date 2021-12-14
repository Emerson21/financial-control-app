package br.com.vr.development.financialcontrolapp.application.inbound;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.service.ContaService;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.FormularioAberturaConta;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/conta/v1")
public class ContaResource {

    @Value("${conta.abertura.valorMinimo}")
    private BigDecimal valorMinimoPermitido;

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity aberturaContaCorrente(@RequestBody @Valid FormularioAberturaConta formularioAberturaConta) {
        log.info("Formulario Abertura Conta {}", formularioAberturaConta);  

        ContaCorrente contaCorrente = formularioAberturaConta.toContaCorrente(valorMinimoPermitido);

        contaService.abrir(contaCorrente);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
