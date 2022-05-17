package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.common.SucessoResponse;
import br.com.vr.development.financialcontrolapp.exception.ContaNotFoundException;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.Transacao;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/transacoes")
@RestController
@AllArgsConstructor
public class TransacaoResource {

    private static final String CONTENT_TYPE_TRANSACAO = "application/vnd.transacoes.v1+json";

    private TransacoesService transacoesService;

    private ContaRepository contaRepository;

    @PostMapping(consumes = CONTENT_TYPE_TRANSACAO)
    public ResponseEntity<SucessoResponse> transacionar(@RequestBody Transacao transacao) throws ContaNotFoundException, SaldoInsuficienteException {
        log.info("Transacionando {}", transacao);

        ContaCorrente contaCorrente = contaRepository.findBy(new Cpf(transacao.getCpf())).orElseThrow(ContaNotFoundException::new);
        ContaDestino contaDestino = transacao.getConta().toModel();

        Transferencia transferencia = new Transferencia(transacao.getValor().toModel(), contaCorrente, contaDestino, transacao.getTipo());

        transacoesService.transacionar(transferencia);
        return ResponseEntity.ok().body(new SucessoResponse("Transação realizada com sucesso."));
    }

}
