package br.com.vr.development.financialcontrolapp.application.domain.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cnpj;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.repository.BancoRepository;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public ContaCorrente abrir(ContaCorrente contaCorrente) {
        log.info("Iniciando cadastro da conta corrente.");

        Banco banco =  bancoRepository.findByCodigo(contaCorrente.getAgencia().getBanco().getCodigo()).orElse(criaNovoBanco(contaCorrente));
        AgenciaBancaria agencia = banco.getAgencias().stream().findFirst().orElse(criaNovaAgencia(contaCorrente, banco));

        contaCorrente.setAgencia(agencia);
        contaCorrente.getLancamentos().forEach(lancamento -> lancamento.addContaCorrente(contaCorrente));

        return contaRepository.save(contaCorrente);

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
            .cnpj(new Cnpj("42500796000191"))
            .codigo("077")
            .codigo(contaCorrente.getAgencia().getBanco().getCodigo())
            .nomeFantasia(contaCorrente.getAgencia().getBanco().getNomeFantasia())
            .build();

        banco.setAgencias(Arrays.asList(criaNovaAgencia(contaCorrente, banco)));
        return banco;
    }
    
}