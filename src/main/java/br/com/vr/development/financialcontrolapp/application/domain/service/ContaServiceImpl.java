package br.com.vr.development.financialcontrolapp.application.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.exception.BancoInvalidoException;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.BancoRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Override
    public void abrir(ContaCorrente contaCorrente) {
        log.info("Iniciando cadastro da conta corrente.");
        bancoRepository.findByCodigo(contaCorrente.getCodigoBanco())
            .orElseThrow(BancoInvalidoException::new);

        contaRepository.save(contaCorrente);
    }
    
}