package br.com.vr.development.financialcontrolapp.application.domain.model;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.vr.development.financialcontrolapp.fixtures.CorrentistaFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.exception.DepositoInicialException;

public class ContaCorrenteTest {

    private static final BigDecimal CINQUENTA = new BigDecimal("50");

    private DepositoInicialFactory depositoInicialFactory;

    @BeforeEach
    protected void setUp() {
        this.depositoInicialFactory = new DepositoInicialFactory(new BigDecimal(50));
    }

    @Test
    public void deveConstruirContaCorrente() {
        Banco banco = getBanco();
        AgenciaBancaria agenciaBancaria = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agenciaBancaria));

        ContaCorrente contaCorrente = new ContaCorrente(agenciaBancaria, CorrentistaFixture.create(), depositoInicialFactory.create(CINQUENTA));
        Assertions.assertNotNull(contaCorrente);
    }

    @Test
    public void naoDeveConstruirContaCorrenteComDepositoInicialMenorQue50() {
        Banco banco = getBanco();
        AgenciaBancaria agenciaBancaria = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agenciaBancaria));

        DepositoInicialException valorMinimoInvalido = Assertions.assertThrows(DepositoInicialException.class, () -> {
            new ContaCorrente(agenciaBancaria, CorrentistaFixture.create(), depositoInicialFactory.create(new BigDecimal("49.9")));    ;
        }, "Valor inicial depositado menor que o permitido.");

        
        Assertions.assertEquals("Valor inicial depositado menor que o permitido.", valorMinimoInvalido.getMessage());
    }

    private AgenciaBancaria getAgenciaBancaria(Banco banco) {
        return AgenciaBancaria.builder().digito(1).numero(1).banco(banco).build();
    }

    private Banco getBanco() {
        return Banco.builder().cnpj(new Cnpj("73811174000108")).codigo("1").nomeFantasia(new NomeFantasia("Banco VR")).build();
    }

    private ContaCorrente criaContaCorrente(BigDecimal valorInicial) {
        return new ContaCorrente(getAgenciaBancaria(getBanco()), CorrentistaFixture.create(), depositoInicialFactory.create(valorInicial));
    }


}
