package br.com.vr.development.financialcontrolapp.inbound.resources;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.vr.development.financialcontrolapp.application.inbound.ContaResource;
import br.com.vr.development.financialcontrolapp.exception.BancoExceptionHandler;

@WebMvcTest
@TestInstance(Lifecycle.PER_CLASS)
public class ContaResourceTest {

    @Value("${conta.abertura.valorMinimo}")
    private BigDecimal valorMinimoPermitidoParaAberturaDaConta;


    @InjectMocks
    private ContaResource contaResource;

    private MockMvc mockMvc;

    @BeforeAll
    public void init() {
		ReflectionTestUtils.setField(contaResource, "valorMinimoPermitidoParaAberturaDaConta", valorMinimoPermitidoParaAberturaDaConta);

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.contaResource)
            .setControllerAdvice(new BancoExceptionHandler())
            .build();
    }

    @Test
    public void deveRetornarStatus_201_AoReceberDadosParaAberturaDaContaCorrente() throws Exception {

        String payload = "{\"prospect\":{\"nome\":{\"primeiroNome\":\"Emerson\",\"sobrenome\":\"Haraguchi\",\"nomeCompleto\":\"Emerson Haraguchi\"}," + 
            "\"documento\":{\"numero\":\"29222004000\",\"valido\":true,\"tipoDocumento\":\"CPF\"},\"dataDeNascimento\":{\"data\":[1988,10,21]}},\"endereco\":{\"cep\":\"13940-970\",\"logradouro\":\"Avenida Brasil 160\",\"numero\":\"607\",\"estado\":\"SAO_PAULO\",\"complemento\":null,\"bairro\":\"Centro\",\"municipio\":\"Águas de Lindóia\"},\"telefone\":{\"ddd\":\"19\",\"numero\":\"2901-7197\"},\"email\":{\"email\":\"thomascauajorgebarbosa-98@agnet.com.br\"},\"renda\":{\"valor\":2000},\"valorDepositoAbertura\":50}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/conta")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void deveRetornarStatus_422_paraValorAberturaMenorQue50() throws Exception {

        String payload = "{\"prospect\":{\"nome\":{\"primeiroNome\":\"Emerson\",\"sobrenome\":\"Haraguchi\",\"nomeCompleto\":\"Emerson Haraguchi\"}," + 
            "\"documento\":{\"numero\":\"29222004000\",\"valido\":true,\"tipoDocumento\":\"CPF\"},\"dataDeNascimento\":{\"data\":[1988,10,21]}}," + 
            "\"endereco\":{\"cep\":\"13940-970\",\"logradouro\":\"Avenida Brasil 160\",\"numero\":\"607\",\"estado\":\"SAO_PAULO\",\"complemento\":null,\"bairro\":\"Centro\",\"municipio\":\"Águas de Lindóia\"},\"telefone\":{\"ddd\":\"19\",\"numero\":\"2901-7197\"},\"email\":{\"email\":\"thomascauajorgebarbosa-98@agnet.com.br\"},\"renda\":{\"valor\":2000},\"valorDepositoAbertura\":49.99}";


        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/conta")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void deveRetornarStatus_400_paraPessoaNuloNoFormularioAbertura() throws Exception {

        String payload = "{\"documento\":{\"numero\":\"29222004000\",\"valido\":true,\"tipoDocumento\":\"CPF\"},\"dataDeNascimento\":{\"data\":[1988,10,21]}}," + 
            "\"endereco\":{\"cep\":\"13940-970\",\"logradouro\":\"Avenida Brasil 160\",\"numero\":\"607\",\"estado\":\"SAO_PAULO\",\"complemento\":null,\"bairro\":\"Centro\",\"municipio\":\"Águas de Lindóia\"},\"telefone\":{\"ddd\":\"19\",\"numero\":\"2901-7197\"},\"email\":{\"email\":\"thomascauajorgebarbosa-98@agnet.com.br\"},\"renda\":{\"valor\":2000},\"valorDepositoAbertura\":50}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/conta")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void deveRetornarStatus_400_paraDocumentoNuloNoFormularioAbertura() throws Exception {

        String payload = "{\"prospect\":{\"nome\":{\"primeiroNome\":\"Emerson\",\"sobrenome\":\"Haraguchi\",\"nomeCompleto\":\"Emerson Haraguchi\"}," + 
        "\"dataDeNascimento\":{\"data\":[1988,10,21]}}," + 
        "\"endereco\":{\"cep\":\"13940-970\",\"logradouro\":\"Avenida Brasil 160\",\"numero\":\"607\",\"estado\":\"SAO_PAULO\",\"complemento\":null,\"bairro\":\"Centro\",\"municipio\":\"Águas de Lindóia\"},\"telefone\":{\"ddd\":\"19\",\"numero\":\"2901-7197\"},\"email\":{\"email\":\"thomascauajorgebarbosa-98@agnet.com.br\"},\"renda\":{\"valor\":2000},\"valorDepositoAbertura\":50}";


        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/conta")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
