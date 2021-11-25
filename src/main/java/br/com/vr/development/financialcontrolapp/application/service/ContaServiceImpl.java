package br.com.vr.development.financialcontrolapp.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.application.domain.ContaCorrente;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void abrir(ContaCorrente contaCorrente) {
        log.info("Iniciando cadastro da conta corrente.");

        br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente entity = 
            new ModelMapper().map(contaCorrente, br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente.class);

        contaRepository.save(entity);
    }
    
}
