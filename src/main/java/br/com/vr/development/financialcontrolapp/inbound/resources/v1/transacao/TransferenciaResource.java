package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaDestinoBuilder;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.TransferenciaComposite;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.common.SucessoResponse;
import br.com.vr.development.financialcontrolapp.exception.ContaNotFoundException;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.Transacao;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/transferencia")
@RestController
@AllArgsConstructor
public class TransferenciaResource {

    private static final String CONTENT_TYPE_TRANSACAO = "application/vnd.transferencia.v1+json";

    private ContaRepository contaRepository;

    private TransferenciaComposite composite;

    @CircuitBreaker(name = "ms-central-bank-cb", fallbackMethod = "fallback")
    @PostMapping(consumes = CONTENT_TYPE_TRANSACAO)
    public ResponseEntity<SucessoResponse> transferir(@RequestBody Transacao transacao) throws ContaNotFoundException, SaldoInsuficienteException {
        log.info("Transacionando {}", transacao);

        /*
            Não pode processar a mesma requisição mais do que 1 única vez.
            Em uma transferencia eu não consigo ser idempotente, porque eu posso ficar realizando diversas transferencias
            e como não há um identificador de unicidade e o metódo é post eu não consigo controlar o fato de ter multiplas
            requisições.
        */

        ContaCorrente contaOrigem = contaRepository.findBy(transacao.getCpfModel()).orElseThrow(ContaNotFoundException::new);
        ContaDestino contaDestino = new ContaDestinoBuilder(transacao.getConta(), contaOrigem.getBanco(), contaRepository).build();

        composite.selecionarTransferencia(transacao.getConta(), contaOrigem.getBanco())
                .transacionar(transacao.toValorModel(), contaOrigem, contaDestino, transacao.getTipo());

        return ResponseEntity.ok().body(new SucessoResponse("Transação realizada com sucesso."));
    }

    private ResponseEntity<SucessoResponse> fallback(RuntimeException e) {
        log.error("Executando FallBack do CircuitBreak ", e);
        throw e;
    }

    //TODO - como está o comportamento ao se deparar com um fallback (transação está funcionando?, testes?)
    //TODO - Circuit Break (Resilient4j)
    //TODO - AccessToken pattern
    //TODO - Event sourcing for auditing logging pattern

}
