package br.com.vr.development.financialcontrolapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.vr.development.financialcontrolapp.application.service.ContaServiceImpl;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente;

public class ContaServiceTest {
    
    @InjectMocks
    private ContaServiceImpl contaService;

    @Mock
    private ContaRepository contaRepository;


    @Test
    public void deveAbrirContaCorrente() {

        Mockito.doNothing().when(contaRepository.save(Mockito.any(ContaCorrente.class)))


    }

}
