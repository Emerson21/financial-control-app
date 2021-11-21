package br.com.vr.development.financialcontrolapp.application.inbound;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.development.financialcontrolapp.application.inbound.dto.FormularioAberturaConta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/conta")
@AllArgsConstructor
public class ContaResource {
    
    @PostMapping
    public ResponseEntity aberturaContaCorrente(@RequestBody @Valid FormularioAberturaConta formularioAberturaConta) {
        log.info("Formulario Abertura Conta {}", formularioAberturaConta);  
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}
