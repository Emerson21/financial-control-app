package br.com.vr.development.financialcontrolapp.application.model;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.exception.DepositoInicialException;

class DepositoInicialFactoryTest {

    private DepositoInicialFactory depositoInicialFactory;
    private BigDecimal valorMinimoPermitido = new BigDecimal("50.00");

    @BeforeEach
    void setUp() {
        depositoInicialFactory = new DepositoInicialFactory(valorMinimoPermitido);
    }


    @Test
    void deveCriarUmDepositoInicial() {
        Assertions.assertNotNull(depositoInicialFactory.create(valorMinimoPermitido));
    }

    @Test
    void deveLancarDepositoInicialExceptionAoCriarUmDepositoInicialComValorMenorQueOPermitido() {
        BigDecimal valorDoDeposito = valorMinimoPermitido.subtract(new BigDecimal("0.01"));
        Assertions.assertThrows(DepositoInicialException.class, () -> {
            depositoInicialFactory.create(valorDoDeposito);
        }, DepositoInicialException.MESSAGE);
    }


}
