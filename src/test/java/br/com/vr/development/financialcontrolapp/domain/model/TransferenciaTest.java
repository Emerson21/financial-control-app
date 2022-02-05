package br.com.vr.development.financialcontrolapp.domain.model;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.TEF;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import br.com.vr.development.financialcontrolapp.fixtures.AgenciaBancariaFixture;
import br.com.vr.development.financialcontrolapp.fixtures.CorrentistaFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;

class TransferenciaTest {

    @Test
    void deveRealizarUmTEFDeUmaContaCorrenteParaOutra() {
        Conta contaOrigem = getContaOrigem();
        Conta contaDestino = getContaDestino();

        new Transferencia(new Valor("10"), (ContaOrigem) contaOrigem, contaDestino, TEF).execute();
        Assertions.assertThat(contaOrigem.getSaldo()).isEqualTo(new Valor("40"));
        Assertions.assertThat(contaDestino.getSaldo()).isEqualTo(new Valor("60"));
    }

    @Test
    void deveLancarExceptionSaldoInsuficienteExceptionQuandoNaoHouverValorDisponivelParaSaque() {
        Assertions.assertThatThrownBy(() -> {
            new Transferencia(new Valor("50,01"), (ContaOrigem) getContaOrigem(), getContaDestino(), TEF).execute();
        });

    }

    @Test
    void deveRealizarUmaTEFDeContaPoupancaParaContaCorrente() { 
        Conta poupanca = getContaPoupanca();
        Conta origem = getContaOrigem();

        new Transferencia(new Valor("0.01"), (ContaOrigem) origem, poupanca, TEF).execute();
        Assertions.assertThat(origem.getSaldo()).isEqualTo(new Valor("49.99"));
        Assertions.assertThat(poupanca.getSaldo()).isEqualTo(new Valor("50.01"));

    }

    private ContaCorrente getContaDestino() {
        Banco banco = Banco.builder()
            .codigo("321")
            .nomeFantasia(new NomeFantasia("nome"))
            .build();
       
        AgenciaBancaria agencia = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agencia));

        return new ContaCorrente(agencia, CorrentistaFixture.create(),
            new DepositoInicialFactory(new BigDecimal("50")).create(new BigDecimal("50")));
            
    }

    private Conta getContaOrigem() {
        Banco banco = Banco.builder()
            .codigo("123")
            .nomeFantasia(new NomeFantasia("nome"))
            .build();
       
        AgenciaBancaria agencia = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agencia));

        return new ContaCorrente(agencia, CorrentistaFixture.create(),
            new DepositoInicialFactory(new BigDecimal("50")).create(new BigDecimal("50")));
    }

    private Conta getContaPoupanca() {
        Banco banco = Banco.builder()
            .codigo("123")
            .nomeFantasia(new NomeFantasia("nome"))
            .build();
       
        AgenciaBancaria agencia = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agencia));

        return new Poupanca(agencia, CorrentistaFixture.create(),
            new DepositoInicialFactory(new BigDecimal("50")).create(new BigDecimal("50")));
    }

    private AgenciaBancaria getAgenciaBancaria(Banco banco) {
        AgenciaBancaria agencia = AgenciaBancaria.builder()
            .banco(banco)
            .numero(123)
            .digito(7)
            .build();
        return agencia;
    }

}

