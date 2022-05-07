package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao;

import br.com.vr.development.financialcontrolapp.application.domain.service.TransacoesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/transacoes/v1")
@RestController
@AllArgsConstructor
public class TransacaoResource {

    private TransacoesService transacoesService;

    @PostMapping
    public ResponseEntity<?> transacionar(@RequestBody TransacaoDTO transacaoDTO) {
        log.info("Transacionando {}", transacaoDTO);
        transacoesService.transacionar(transacaoDTO);
        return ResponseEntity.ok().build();
    }

}
