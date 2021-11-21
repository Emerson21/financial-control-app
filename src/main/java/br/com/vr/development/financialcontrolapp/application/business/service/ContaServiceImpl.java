package br.com.vr.development.financialcontrolapp.application.business.service;

import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
public class ContaServiceImpl implements ContaService {

    private ContaRepository contaRepository;

    @Override
    public void abrir() {
        log.info("Iniciando cadastro da conta corrente.");
    }
    
}
