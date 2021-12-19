package br.com.vr.development.financialcontrolapp.application.domain.service.agenciabancaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.exception.AgenciaInvalidaException;
import br.com.vr.development.financialcontrolapp.repository.AgenciaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AgenciaBancariaServiceImpl implements AgenciaBancariaService  {
    
    @Autowired
    private AgenciaRepository agenciaRepository;


    @Override
    public AgenciaBancaria findBy(Long id) {
        log.info("Buscando agencia bancaria pelo id {}", id);
        return agenciaRepository.findById(id).orElseThrow(AgenciaInvalidaException::new);
    }
    
}
