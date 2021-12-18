package br.com.vr.development.financialcontrolapp.application.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public ContaCorrente abrir(ContaCorrente contaCorrente) {
        log.info("Iniciando cadastro da conta corrente.");

        return contaRepository.save(contaCorrente);
    }
    
}