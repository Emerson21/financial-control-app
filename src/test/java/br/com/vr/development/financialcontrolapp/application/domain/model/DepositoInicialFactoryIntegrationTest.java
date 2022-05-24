package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.exception.DepositoInicialException;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DepositoInicialFactoryIntegrationTest {
    
    private DepositoInicialFactory depositoInicialFactory;

    @BeforeEach
    public void setup(){
        depositoInicialFactory = new DepositoInicialFactory(new BigDecimal("50"));
    }

    @Test
    public void deveCriarDepositoInicial() {
        Assertions.assertNotNull(depositoInicialFactory.create(new BigDecimal("50")));
    }

    @Test
    public void naoDeveCriarDepositoInicialComValorMenorDoQueOMinimo() {
        Assertions.assertThrows(DepositoInicialException.class, () -> {
            depositoInicialFactory.create(new BigDecimal("49.99"));
        }, "Valor inicial depositado menor que o permitido.");
    }

    @Test
    public void naoDeveCriarDepositoInicialComValorNulo() {
        Assertions.assertThrows(DepositoInicialException.class, () -> {
            depositoInicialFactory.create(null);
        }, "Valor inicial depositado menor que o permitido.");
    }

}
