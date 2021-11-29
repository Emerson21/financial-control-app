package br.com.vr.development.financialcontrolapp.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.vr.development.financialcontrolapp.application.commons.Celular;
import br.com.vr.development.financialcontrolapp.application.commons.Cpf;
import br.com.vr.development.financialcontrolapp.application.commons.Email;
import br.com.vr.development.financialcontrolapp.application.commons.Endereco;
import br.com.vr.development.financialcontrolapp.application.commons.EnderecoResidencial;
import br.com.vr.development.financialcontrolapp.application.domain.Renda;
import br.com.vr.development.financialcontrolapp.application.enums.UF;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.DataNascimento;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.FormularioAberturaConta;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.Nome;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.Pessoa;
import br.com.vr.development.financialcontrolapp.application.service.ContaServiceImpl;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import br.com.vr.development.financialcontrolapp.repository.entities.Agencia;
import br.com.vr.development.financialcontrolapp.repository.entities.Banco;
import br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente;

@TestInstance(Lifecycle.PER_CLASS)
public class ContaServiceTest {

    
    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private ContaServiceImpl contaService;

    @Mock
    private ContaRepository contaRepository;


    @Test
    public void deveAbrirContaCorrente() {

        Pessoa pessoa = new Pessoa(
            new Nome("Emerson", "Haraguchi"), 
            new Cpf("29222004000"), 
            new DataNascimento(LocalDate.of(1988, 10, 21)));

        EnderecoResidencial endereco = (EnderecoResidencial) getEndereco();

        Celular telefone = new Celular("19", "2901-7197");
        Email email = new Email("thomascauajorgebarbosa-98@agnet.com.br");
        Renda renda = new Renda(new BigDecimal("2000"));

        FormularioAberturaConta formulario = new FormularioAberturaConta(pessoa, endereco, telefone, email, renda, new BigDecimal("50"));
        ContaCorrente entity = getContaCorrenteEntity(formulario.toContaCorrente());

        when(contaRepository.save(Mockito.any(ContaCorrente.class))).thenReturn(entity);
        br.com.vr.development.financialcontrolapp.application.domain.ContaCorrente conta = contaService.abrir(formulario.toContaCorrente());

        Assert.assertNotNull(conta);

    }


    private ContaCorrente getContaCorrenteEntity(br.com.vr.development.financialcontrolapp.application.domain.ContaCorrente contaCorrente) {
        Banco banco = Banco.builder()
            .codigo(contaCorrente.getAgencia().getBanco().getCodigo())
            .nome(contaCorrente.getAgencia().getBanco().getNome().getNomeCompleto())
            .build();

        Agencia agencia = Agencia.builder()
            .banco(banco)
            .numero(contaCorrente.getAgencia().getNumero())
            .digito(contaCorrente.getAgencia().getDigito())
            .build();

        br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente entity = 
            br.com.vr.development.financialcontrolapp.repository.entities.ContaCorrente.builder()
                .agencia(agencia)
                .numero(contaCorrente.getNumero())
                .digito(contaCorrente.getDigito())
                .build();
        
        return entity;
    }


    private Endereco getEndereco() {
        Endereco endereco = new EnderecoResidencial();
        endereco.setCep("13940-970");
        endereco.setBairro("Centro");
        endereco.setMunicipio("Águas de Lindóia");
        endereco.setEstado(UF.SAO_PAULO);
        endereco.setLogradouro("Avenida Brasil 160");
        endereco.setNumero("607");
        return endereco;
    }
}
