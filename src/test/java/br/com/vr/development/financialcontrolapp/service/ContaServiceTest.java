package br.com.vr.development.financialcontrolapp.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.vr.development.financialcontrolapp.fixtures.CorrentistaFixture;
import br.com.vr.development.financialcontrolapp.fixtures.EnderecoFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Celular;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.DataNascimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.Email;
import br.com.vr.development.financialcontrolapp.application.domain.model.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Nome;
import br.com.vr.development.financialcontrolapp.application.domain.model.NomeFantasia;
import br.com.vr.development.financialcontrolapp.application.domain.model.Pessoa;
import br.com.vr.development.financialcontrolapp.application.domain.model.RendaMensal;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.application.domain.service.ContaServiceImpl;
import br.com.vr.development.financialcontrolapp.inbound.v1.dto.AgenciaBancariaDTO;
import br.com.vr.development.financialcontrolapp.inbound.v1.dto.FormularioAberturaConta;
import br.com.vr.development.financialcontrolapp.exception.BancoInvalidoException;
import br.com.vr.development.financialcontrolapp.exception.DepositoInicialException;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.BancoRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.ContaRepository;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class ContaServiceTest {

    @InjectMocks
    private ContaServiceImpl contaService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private BancoRepository bancoRepository;

    @Autowired
    private DepositoInicialFactory depositoInicialFactory;


    @Test
    public void deveAbrirContaCorrente() {

        Pessoa pessoa = getPessoa();

        List<Endereco> enderecos = Arrays.asList(EnderecoFixture.create());

        Celular telefone = new Celular("19", "2901-7197");
        Email email = new Email("thomascauajorgebarbosa-98@agnet.com.br");
        RendaMensal renda = new RendaMensal(new BigDecimal("2000"));
        Banco banco = Banco.builder()
            .codigo("123")
            .nomeFantasia(new NomeFantasia("Banco VR"))
            .build();       

        FormularioAberturaConta formulario = new FormularioAberturaConta(pessoa, enderecos, telefone, email, renda, new BigDecimal("50"), new AgenciaBancariaDTO(1L));
        
        DepositoInicial depositoInicial = depositoInicialFactory.create(formulario.getValorDepositoAbertura());
        ContaCorrente entity = getContaCorrente(formulario.toContaCorrente(getAgenciaBancaria(banco), depositoInicial));

        when(bancoRepository.findByCodigo(banco.getCodigo())).thenReturn(Optional.of(banco));
        when(contaRepository.save(Mockito.any(ContaCorrente.class))).thenReturn(entity);
        contaService.abrir(formulario.toContaCorrente(getAgenciaBancaria(banco), depositoInicial));
    }

    @Test
    public void naoDeveAbrirContaCorrenteComValorMenorQue50() {
        Pessoa pessoa = getPessoa();
        List<Endereco> enderecos = Arrays.asList(EnderecoFixture.create());

        Celular telefone = new Celular("19", "2901-7197");
        Email email = new Email("thomascauajorgebarbosa-98@agnet.com.br");
        RendaMensal renda = new RendaMensal(new BigDecimal("2000"));

        Banco banco = Banco.builder()
            .codigo("123")
            .nomeFantasia(new NomeFantasia("Banco VR"))
            .build();       

        FormularioAberturaConta formulario = 
            new FormularioAberturaConta(pessoa, enderecos, telefone, email, renda, new BigDecimal("49.9"), new AgenciaBancariaDTO(1L));

        DepositoInicialException valorMinimoInvalido = Assertions.assertThrows(DepositoInicialException.class, () -> {
            contaService.abrir(formulario.toContaCorrente(getAgenciaBancaria(banco), depositoInicialFactory.create(formulario.getValorDepositoAbertura())));
        }, "Valor inicial depositado menor que o permitido.");

        Assertions.assertEquals("Valor inicial depositado menor que o permitido.", valorMinimoInvalido.getMessage());
    }


    @Test
    public void naoDeveAbrirContaCorrenteQuandoNaoEncontrarOBancoNoBancoDeDados() {

        Pessoa pessoa = getPessoa();
        List<Endereco> enderecos = Arrays.asList(EnderecoFixture.create());
        Celular telefone = new Celular("19", "2901-7197");
        Email email = new Email("thomascauajorgebarbosa-98@agnet.com.br");
        RendaMensal renda = new RendaMensal(new BigDecimal("2000"));

        Banco banco = Banco.builder()
            .codigo("000")
            .nomeFantasia(new NomeFantasia("Banco VR"))
            .build();       

        FormularioAberturaConta formulario = new FormularioAberturaConta(pessoa, enderecos, telefone, email, renda, new BigDecimal("50"), new AgenciaBancariaDTO(1L));
        
        DepositoInicial depositoInicial = depositoInicialFactory.create(formulario.getValorDepositoAbertura());

        when(bancoRepository.findByCodigo(banco.getCodigo())).thenReturn(Optional.empty());
        
        Assertions.assertThrows(BancoInvalidoException.class, () -> {
            contaService.abrir(formulario.toContaCorrente(getAgenciaBancaria(banco), depositoInicial));
        });
    }


    private Pessoa getPessoa() {
        return new Pessoa(
            new Nome("Emerson", "Haraguchi"), 
            new Cpf("29222004000"), 
            new DataNascimento(LocalDate.of(1988, 10, 21)));
    }


    private ContaCorrente getContaCorrente(ContaCorrente contaCorrente) {
        Banco banco = Banco.builder()
            .codigo(contaCorrente.getCodigoBanco())
            .nomeFantasia(contaCorrente.getNomeFantasia())
            .build();
       
        AgenciaBancaria agencia = getAgenciaBancaria(banco);

        banco.setAgencias(Arrays.asList(agencia));

        return new ContaCorrente(agencia, CorrentistaFixture.create(),
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
