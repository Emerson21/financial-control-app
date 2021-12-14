package br.com.vr.development.financialcontrolapp.application.domain.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Correntista;
import br.com.vr.development.financialcontrolapp.application.domain.model.Lancamento;
import br.com.vr.development.financialcontrolapp.exception.ValorMinimoInvalidoExcepton;
import br.com.vr.development.financialcontrolapp.repository.BancoRepository;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContaServiceImpl implements ContaService {

    @Value("${conta.abertura.valorMinimo}")
    private BigDecimal valorMinimoPermitido;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public ContaCorrente abrir(ContaCorrente contaCorrente) {
        log.info("Iniciando cadastro da conta corrente.");

        this.validaValorMinimo(contaCorrente);

        Banco banco =  bancoRepository.findByCodigo(contaCorrente.getAgencia().getBanco().getCodigo()).orElse(criaNovoBanco(contaCorrente));
        AgenciaBancaria agencia = banco.getAgencias().stream().findFirst().orElse(criaNovaAgencia(contaCorrente, banco));

        Correntista correntista = Correntista.builder()
            .nome(contaCorrente.getCorrentista().getNome())
            .celular(contaCorrente.getCorrentista().getCelular())
            .email(contaCorrente.getCorrentista().getEmail())
            .dataNascimento(contaCorrente.getCorrentista().getDataNascimento())
            .rendaMensal(contaCorrente.getCorrentista().getRendaMensal())
            .tipoDocumento(contaCorrente.getCorrentista().getCpf().getTipoDocumento())
            .cpf(contaCorrente.getCorrentista().getCpf())
            .enderecos(contaCorrente.getCorrentista().getEnderecos())
            .build();

        ContaCorrente entity = ContaCorrente.builder()
                .agencia(agencia)
                .numero(contaCorrente.getNumero())
                .digito(contaCorrente.getDigito())
                .correntista(correntista)
                .build();

        return contaRepository.save(entity);

    }

    private void validaValorMinimo(ContaCorrente contaCorrente) {
        if (!Optional.ofNullable(contaCorrente.getLancamentos()).isPresent()) {
            throw new ValorMinimoInvalidoExcepton();
        }

        Optional.ofNullable(contaCorrente.getLancamentos()).ifPresent(lancamentos -> {
            Lancamento lancamento = lancamentos.stream().findFirst().orElseThrow(ValorMinimoInvalidoExcepton::new);
            if (lancamento.getValor().compareTo(valorMinimoPermitido) < 0) {
                throw new ValorMinimoInvalidoExcepton();
            }
        });

    }

    private AgenciaBancaria criaNovaAgencia(ContaCorrente contaCorrente, Banco banco) {
        return AgenciaBancaria.builder()
            .banco(banco)
            .numero(contaCorrente.getAgencia().getNumero())
            .digito(contaCorrente.getAgencia().getDigito())
            .build();
    }

    private Banco criaNovoBanco(ContaCorrente contaCorrente) {
        Banco banco = Banco.builder()
            .codigo(contaCorrente.getAgencia().getBanco().getCodigo())
            .nomeFantasia(contaCorrente.getAgencia().getBanco().getNomeFantasia())
            .build();

        banco.setAgencias(Arrays.asList(criaNovaAgencia(contaCorrente, banco)));
        return banco;
    }
    
}
