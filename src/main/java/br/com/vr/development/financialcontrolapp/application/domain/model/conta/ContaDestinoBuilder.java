package br.com.vr.development.financialcontrolapp.application.domain.model.conta;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.exception.ContaNotFoundException;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ContaDestinoDTO;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;

public class ContaDestinoBuilder {

    private ContaDestinoDTO conta;
    private Banco banco;
    private ContaRepository contaRepository;

    public ContaDestinoBuilder(ContaDestinoDTO conta, Banco banco, ContaRepository contaRepository) {
        this.conta = conta;
        this.banco = banco;
        this.contaRepository = contaRepository;
    }

    public ContaDestino build() {
        if (conta.banco().equals(banco.getCodigo())) {
            return contaRepository.findBy(conta.getCpfModel()).orElseThrow(ContaNotFoundException::new);
        }

        AgenciaBancaria agenciaBancaria =
            new AgenciaBancaria(
                    Integer.valueOf(conta.getDadosConta().getAgencia()),
                    Integer.valueOf(conta.getDadosConta().getDigitoAgencia())
            );

        return new ContaExterna(new Banco(conta.getDadosConta().getBanco()), agenciaBancaria,
                Integer.valueOf(conta.getDadosConta().getNumeroConta()),
                Integer.valueOf(conta.getDadosConta().getDigitoConta()),
                conta.getNomeCorrentista(), conta.getCpfModel());
    }
}
