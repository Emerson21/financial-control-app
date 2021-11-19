package br.com.vr.development.financialcontrolapp.inbound.resources;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.vr.development.financialcontrolapp.application.commons.Celular;
import br.com.vr.development.financialcontrolapp.application.commons.Cpf;
import br.com.vr.development.financialcontrolapp.application.commons.Email;
import br.com.vr.development.financialcontrolapp.application.commons.Endereco;
import br.com.vr.development.financialcontrolapp.application.commons.EnderecoResidencial;
import br.com.vr.development.financialcontrolapp.application.domain.Renda;
import br.com.vr.development.financialcontrolapp.application.enums.UF;
import br.com.vr.development.financialcontrolapp.application.inbound.ContaResource;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.DataNascimento;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.FormularioAberturaConta;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.Nome;
import br.com.vr.development.financialcontrolapp.application.inbound.dto.Pessoa;
import br.com.vr.development.financialcontrolapp.exception.BancoExceptionHandler;

@WebMvcTest
@TestInstance(Lifecycle.PER_CLASS)
public class ContaResourceTest {
    
    @InjectMocks
    private ContaResource contaResource;

    private MockMvc mockMvc;

    @BeforeAll
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.contaResource)
            .setControllerAdvice(new BancoExceptionHandler())
            .build();
    }

    @Test
    public void deveRetornarStatus_201_AoReceberDadosParaAberturaDaContaCorrente() throws Exception {

        Pessoa pessoa = new Pessoa(
            new Nome("Emerson", "Haraguchi"), 
            new Cpf("29222004000"), 
            new DataNascimento(LocalDate.of(1988, 10, 21)));

        EnderecoResidencial endereco = (EnderecoResidencial) getEndereco();

        Celular telefone = new Celular("19", "2901-7197");
        Email email = new Email("thomascauajorgebarbosa-98@agnet.com.br");
        Renda renda = new Renda(new BigDecimal("2000"));

        FormularioAberturaConta formulario = new FormularioAberturaConta(pessoa, endereco, telefone, email, renda);

        String jsonPayload = new ObjectMapper().registerModule(new JavaTimeModule())
            .writeValueAsString(formulario);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/conta")
            .content(jsonPayload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    private Endereco getEndereco() {
        Endereco endereco = new EnderecoResidencial();
        endereco.setCep("13940-970");
        endereco.setBairro("Centro");
        endereco.setCidade("Águas de Lindóia");
        endereco.setEstado(UF.SAO_PAULO);
        endereco.setLogradouro("Avenida Brasil 160");
        endereco.setNumero("607");
        return endereco;
    }


}
