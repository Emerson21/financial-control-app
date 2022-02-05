package br.com.vr.development.financialcontrolapp.domain.model;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.TEF;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import br.com.vr.development.financialcontrolapp.application.domain.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;
import br.com.vr.development.financialcontrolapp.application.enums.TipoDocumento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoEndereco;
import br.com.vr.development.financialcontrolapp.application.enums.UF;

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

        return new ContaCorrente(agencia, getCorrentista(), 
            new DepositoInicialFactory(new BigDecimal("50")).create(new BigDecimal("50")));
            
    }

    private Conta getContaOrigem() {
        Banco banco = Banco.builder()
            .codigo("123")
            .nomeFantasia(new NomeFantasia("nome"))
            .build();
       
        AgenciaBancaria agencia = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agencia));

        return new ContaCorrente(agencia, getCorrentista(), 
            new DepositoInicialFactory(new BigDecimal("50")).create(new BigDecimal("50")));
    }

    private Conta getContaPoupanca() {
        Banco banco = Banco.builder()
            .codigo("123")
            .nomeFantasia(new NomeFantasia("nome"))
            .build();
       
        AgenciaBancaria agencia = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agencia));

        return new Poupanca(agencia, getCorrentista(), 
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

    private Correntista getCorrentista() {
        Celular telefone = new Celular("19", "2901-7197");
        Email email = new Email("thomascauajorgebarbosa-98@agnet.com.br");
        RendaMensal renda = new RendaMensal(new BigDecimal("2000"));

        return new Correntista(
            new Nome("Emerson", "Haraguchi"),
            new Cpf("29222004000"),
            TipoDocumento.CPF,
            new DataNascimento(LocalDate.of(1988, 10, 21)),
            renda,
            email,
            telefone,
            this.getEnderecos());
    }

    private List<Endereco> getEnderecos() {
        Endereco endereco = new Endereco();
        endereco.setCep("13940-970");
        endereco.setBairro("Centro");
        endereco.setMunicipio("Águas de Lindóia");
        endereco.setEstado(UF.SAO_PAULO);
        endereco.setLogradouro("Avenida Brasil 160");
        endereco.setNumero("607");
        endereco.setTipoEndereco(TipoEndereco.RESIDENCIAL);
        return Arrays.asList(endereco);
    }
}

