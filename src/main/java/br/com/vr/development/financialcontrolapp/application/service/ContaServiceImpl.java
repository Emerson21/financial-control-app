package br.com.vr.development.financialcontrolapp.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.application.domain.ContaCorrente;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import br.com.vr.development.financialcontrolapp.repository.entities.Agencia;
import br.com.vr.development.financialcontrolapp.repository.entities.Banco;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public ContaCorrente abrir(ContaCorrente contaCorrente) {
        log.info("Iniciando cadastro da conta corrente.");

        Banco banco = Banco.builder()
            .codigo(contaCorrente.getAgencia().getBanco().getCodigo())
            .nome(contaCorrente.getAgencia().getBanco().getNome().getNomeCompleto())
            .build();

        Agencia agencia = Agencia.builder()
            .banco(banco)
            .numero(contaCorrente.getAgencia().getNumero())
            .digito(contaCorrente.getAgencia().getDigito())
            .build();


        br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente entity = 
            br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente.builder()
                .agencia(agencia)
                .numero(contaCorrente.getNumero())
                .digito(contaCorrente.getDigito())
                .build();

        contaRepository.save(entity);

        return contaCorrente;
    }
    
}
