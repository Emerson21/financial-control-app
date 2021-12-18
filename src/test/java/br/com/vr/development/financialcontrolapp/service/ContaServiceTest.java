package br.com.vr.development.financialcontrolapp.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.DataNascimento;
import br.com.vr.development.financialcontrolapp.application.domain.model.Email;
import br.com.vr.development.financialcontrolapp.application.domain.model.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Nome;
import br.com.vr.development.financialcontrolapp.application.domain.model.Pessoa;
import br.com.vr.development.financialcontrolapp.application.domain.model.RendaMensal;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicial;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.application.domain.service.ContaServiceImpl;
import br.com.vr.development.financialcontrolapp.application.enums.TipoEndereco;
import br.com.vr.development.financialcontrolapp.application.enums.UF;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.FormularioAberturaConta;
import br.com.vr.development.financialcontrolapp.exception.DepositoInicialException;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;

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

    @Autowired
    private DepositoInicialFactory depositoInicialFactory;


    @Test
    public void deveAbrirContaCorrente() {

        Pessoa pessoa = new Pessoa(
            new Nome("Emerson", "Haraguchi"), 
            new Cpf("29222004000"), 
            new DataNascimento(LocalDate.of(1988, 10, 21)));

        List<Endereco> enderecos = getEnderecos();

        Celular telefone = new Celular("19", "2901-7197");
        Email email = new Email("thomascauajorgebarbosa-98@agnet.com.br");
        RendaMensal renda = new RendaMensal(new BigDecimal("2000"));

        FormularioAberturaConta formulario = new FormularioAberturaConta(pessoa, enderecos, telefone, email, renda, new BigDecimal("50"));
        
        DepositoInicial depositoInicial = depositoInicialFactory.create(formulario.getValorDepositoAbertura());

        // ContaCorrente contaCorrente = formulario.toContaCorrente(depositoInicial);
        ContaCorrente entity = getContaCorrente(formulario.toContaCorrente(depositoInicial));

        when(contaRepository.save(Mockito.any(ContaCorrente.class))).thenReturn(entity);
        ContaCorrente conta = contaService.abrir(formulario.toContaCorrente(depositoInicial));

        Assertions.assertNotNull(conta);
    }

    @Test
    public void naoDeveAbrirContaCorrenteComValorMenorQue50() {
        Pessoa pessoa = new Pessoa(
            new Nome("Emerson", "Haraguchi"), 
            new Cpf("29222004000"), 
            new DataNascimento(LocalDate.of(1988, 10, 21)));

        List<Endereco> enderecos = getEnderecos();

        Celular telefone = new Celular("19", "2901-7197");
        Email email = new Email("thomascauajorgebarbosa-98@agnet.com.br");
        RendaMensal renda = new RendaMensal(new BigDecimal("2000"));
        FormularioAberturaConta formulario = new FormularioAberturaConta(pessoa, enderecos, telefone, email, renda, new BigDecimal("49.9"));

        DepositoInicialException valorMinimoInvalido = Assertions.assertThrows(DepositoInicialException.class, () -> {
            contaService.abrir(formulario.toContaCorrente(depositoInicialFactory.create(formulario.getValorDepositoAbertura())));
        }, "Valor minimo para abertura da conta corrente menor que o permitido");

        
        Assertions.assertEquals("Valor minimo para abertura da conta corrente menor que o permitido", valorMinimoInvalido.getMessage());

    }

    private ContaCorrente getContaCorrente(ContaCorrente contaCorrente) {
        Banco banco = Banco.builder()
            .codigo(contaCorrente.getAgencia().getBanco().getCodigo())
            .nomeFantasia(contaCorrente.getAgencia().getBanco().getNomeFantasia())
            .build();
       
        AgenciaBancaria agencia = AgenciaBancaria.builder()
            .banco(banco)
            .numero(contaCorrente.getAgencia().getNumero())
            .digito(contaCorrente.getAgencia().getDigito())
            .build();

        banco.setAgencias(Arrays.asList(agencia));

        ContaCorrente entity = ContaCorrente.builder()
                .agencia(agencia)
                .numero(contaCorrente.getNumero())
                .digito(contaCorrente.getDigito())
                .build();
        
        return entity;
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
