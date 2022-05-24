package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaExterna;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.common.SucessoResponse;
import br.com.vr.development.financialcontrolapp.exception.ContaNotFoundException;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ContaDestinoDTO;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.Transacao;
import br.com.vr.development.financialcontrolapp.infrastructure.gateway.TransferenciaExternaClient;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;
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

    private TransferenciaExternaClient transferenciaExternaClient;

    @PostMapping(consumes = CONTENT_TYPE_TRANSACAO)
    public ResponseEntity<SucessoResponse> transacionar(@RequestBody Transacao transacao) throws ContaNotFoundException, SaldoInsuficienteException {
        log.info("Transacionando {}", transacao);

        ContaCorrente contaOrigem = getContaOrigem(transacao.getCpfModel());
        ContaDestino contaDestino = getContaDestino(transacao.getConta(), contaOrigem);

        Transferencia transferencia = new Transferencia(transacao.toValorModel(), contaOrigem, contaDestino, transacao.getTipo());

        transacoesService.transacionar(transferencia);
        return ResponseEntity.ok().body(new SucessoResponse("Transação realizada com sucesso."));
    }

    private ContaDestino getContaDestino(ContaDestinoDTO conta, ContaCorrente contaOrigem) {
        if (conta.banco().equals(contaOrigem.getBanco().getCodigo())) {
            return getContaOrigem(conta.getCpfModel());
        }

        AgenciaBancaria agenciaBancaria = new AgenciaBancaria(Integer.valueOf(conta.getDadosConta().getAgencia()),
                Integer.valueOf(conta.getDadosConta().getDigitoAgencia()));

        return new ContaExterna(new Banco(conta.getDadosConta().getBanco()), agenciaBancaria,
                Integer.valueOf(conta.getDadosConta().getNumeroConta()),
                Integer.valueOf(conta.getDadosConta().getDigitoConta()),
                conta.getNomeCorrentista(), conta.getCpfModel(), transferenciaExternaClient);
    }

    private ContaCorrente getContaOrigem(Cpf cpf) {
        return contaRepository.findBy(cpf).orElseThrow(ContaNotFoundException::new);
    }

}
