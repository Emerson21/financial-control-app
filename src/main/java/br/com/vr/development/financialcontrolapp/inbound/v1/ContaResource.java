package br.com.vr.development.financialcontrolapp.inbound.v1;

import javax.validation.Valid;

import br.com.vr.development.financialcontrolapp.application.domain.service.ContaService;
import br.com.vr.development.financialcontrolapp.inbound.v1.dto.FormularioAberturaConta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.application.domain.service.agenciabancaria.AgenciaBancariaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/conta/v1")
@AllArgsConstructor
public class ContaResource {

    private DepositoInicialFactory depositoInicialFactory;
    private ContaService contaService;
    private AgenciaBancariaService agenciaBancariaService;

    @PostMapping
    public ResponseEntity aberturaContaCorrente(@RequestBody @Valid FormularioAberturaConta formularioAberturaConta) {
        log.info("Formulario Abertura Conta {}", formularioAberturaConta);  

        AgenciaBancaria agenciaBancaria = agenciaBancariaService.findBy(formularioAberturaConta.getAgenciaBancaria().getId());

        ContaCorrente contaCorrente = formularioAberturaConta
            .toContaCorrente(agenciaBancaria, depositoInicialFactory.create(formularioAberturaConta.getValorDepositoAbertura()));

        contaService.abrir(contaCorrente);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
