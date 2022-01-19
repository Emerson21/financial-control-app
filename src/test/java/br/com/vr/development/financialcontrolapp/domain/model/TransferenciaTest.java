package br.com.vr.development.financialcontrolapp.domain.model;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.TEF;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Celular;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Correntista;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.DataNascimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.Email;
import br.com.vr.development.financialcontrolapp.application.domain.model.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Nome;
import br.com.vr.development.financialcontrolapp.application.domain.model.NomeFantasia;
import br.com.vr.development.financialcontrolapp.application.domain.model.RendaMensal;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
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
        ContaOrigem contaOrigem = getContaOrigem();
        ContaDestino contaDestino = getContaDestino();

        new Transferencia(new Valor("10"), contaOrigem, contaDestino).execute(TEF);
        Assertions.assertThat(contaOrigem.getSaldo()).isEqualTo(new Valor("40"));
    }

    @Test
    void deveRealizarUmaTEFDeContaCorrenteParaContaPoupanca() {
        ContaOrigem contaOrigem = getContaOrigem();
        ContaDestino contaDestino = getContaDestino();

        new Transferencia(new Valor("10"), contaOrigem, contaDestino).execute(TEF);
        Assertions.assertThat(contaOrigem.getSaldo()).isEqualTo(new Valor("40"));

    }

    @Test
    void deveLancarExceptionSaldoInsuficienteExceptionQuandoNaoHouverValorDisponivelParaSaque() {

    }

    @Test
    void deveRealizarUmaTEFDeContaPoupancaParaContaCorrente() { }



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

    private ContaOrigem getContaOrigem() {
        Banco banco = Banco.builder()
            .codigo("123")
            .nomeFantasia(new NomeFantasia("nome"))
            .build();
       
        AgenciaBancaria agencia = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agencia));

        return new ContaCorrente(agencia, getCorrentista(), 
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

        return Correntista.builder()
            .nome(new Nome("Emerson", "Haraguchi"))
            .email(email)
            .enderecos(this.getEnderecos())
            .cpf(new Cpf("29222004000"))
            .tipoDocumento(TipoDocumento.CPF)
            .dataNascimento(new DataNascimento(LocalDate.of(1988, 10, 21)))
            .celular(telefone)
            .rendaMensal(renda)
        .build();
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
