package br.com.vr.development.financialcontrolapp.application.domain.model;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.TEF;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Conta;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Poupanca;
import br.com.vr.development.financialcontrolapp.exception.SaldoInsuficienteException;
import br.com.vr.development.financialcontrolapp.fixtures.CorrentistaFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;

class TransferenciaTest {

    @Test
    void deveRealizarUmTEFDeUmaContaCorrenteParaOutra() throws SaldoInsuficienteException {
        Conta contaOrigem = getContaOrigem();
        Conta contaDestino = getContaDestino();

        new Transferencia(new Valor("10"), contaOrigem, contaDestino, TEF).execute();
        assertThat(contaOrigem.getSaldo()).isEqualTo(new Valor("40"));
        assertThat(contaDestino.getSaldo()).isEqualTo(new Valor("60"));
    }

    @Test
    void deveLancarExceptionSaldoInsuficienteExceptionQuandoNaoHouverValorDisponivelParaSaque() {
        Assertions.assertThatThrownBy(() -> {
            new Transferencia(new Valor("50,01"), getContaOrigem(), getContaDestino(), TEF).execute();
        });

    }

    @Test
    void deveRealizarUmaTEFDeContaPoupancaParaContaCorrente() throws SaldoInsuficienteException {
        Conta poupanca = getContaPoupanca();
        Conta origem = getContaOrigem();

        new Transferencia(new Valor("0.01"), origem, poupanca, TEF).execute();
        assertThat(origem.getSaldo()).isEqualTo(new Valor("49.99"));
        assertThat(poupanca.getSaldo()).isEqualTo(new Valor("50.01"));

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

