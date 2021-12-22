package br.com.vr.development.financialcontrolapp.domain.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;

@SpringBootTest
public class DepositoInicialFactoryTest {
    
    @Autowired
    private DepositoInicialFactory depositoInicialFactory;


    @Test
    public void deveCriarDepositoInicial() {
        Assertions.assertNotNull(depositoInicialFactory.create(new BigDecimal("50")));
    }


}
