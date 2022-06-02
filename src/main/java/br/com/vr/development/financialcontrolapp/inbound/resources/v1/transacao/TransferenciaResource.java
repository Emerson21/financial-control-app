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
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransacaoRepository;
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
    private TransacaoRepository transacaoRepository;

    @PostMapping(consumes = CONTENT_TYPE_TRANSACAO)
    public ResponseEntity<SucessoResponse> transferir(@RequestBody Transacao transacao) throws ContaNotFoundException, SaldoInsuficienteException {
        log.info("Transacionando {}", transacao);

        if (!transacaoRepository.findByCpfAndDataHora(transacao.getCpf(), transacao.getDataHora()).isPresent()) {
            Transacao entity = transacaoRepository.save(transacao);
            try {
                ContaCorrente contaOrigem = contaRepository.findBy(transacao.getCpfModel()).orElseThrow(ContaNotFoundException::new);
                ContaDestino contaDestino = new ContaDestinoBuilder(transacao.getConta(), contaOrigem.getBanco(), contaRepository).build();

                composite.selecionarTransferencia(transacao.getConta(), contaOrigem.getBanco())
                        .transacionar(transacao.toValorModel(), contaOrigem, contaDestino, transacao.getTipo());

            } catch (Exception e) {
                log.error("Erro {}", e);
                transacaoRepository.delete(entity);
            }
        }

        return ResponseEntity.ok().body(new SucessoResponse("Transação realizada com sucesso."));
    }

    //TODO - AccessToken pattern
    //TODO - Event sourcing for auditing logging pattern

}
