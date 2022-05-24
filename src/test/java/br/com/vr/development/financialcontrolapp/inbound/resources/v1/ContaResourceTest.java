package br.com.vr.development.financialcontrolapp.inbound.resources.v1;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.vr.development.financialcontrolapp.application.domain.model.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cnpj;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.NomeFantasia;
import br.com.vr.development.financialcontrolapp.application.domain.model.components.DepositoInicialFactory;
import br.com.vr.development.financialcontrolapp.application.domain.service.ContaService;
import br.com.vr.development.financialcontrolapp.application.domain.service.agenciabancaria.AgenciaBancariaService;
import br.com.vr.development.financialcontrolapp.inbound.v1.ContaResource;
import br.com.vr.development.financialcontrolapp.exception.FinancialExceptionHandler;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ContaResourceTest {

    @InjectMocks
    private ContaResource contaResource;

    @Mock
    private ContaService contaService;

    @Mock
    private AgenciaBancariaService agenciaBancariaService;

    private MockMvc mockMvc;

    @BeforeAll
    public void init() {
        contaResource = new ContaResource(new DepositoInicialFactory(new BigDecimal("50")), contaService, agenciaBancariaService);

        mockMvc = MockMvcBuilders.standaloneSetup(this.contaResource)
            .setControllerAdvice(new FinancialExceptionHandler())
            .build();
    }

    @Test
    void deveRetornarStatus_201_AoReceberDadosParaAberturaDaContaCorrente() throws Exception {

        String payload = "{\"prospect\":{\"nome\":{\"primeiroNome\":\"Emerson\",\"sobrenome\":\"Haraguchi\",\"nomeCompleto\":\"Emerson Haraguchi\"}," + 
            "\"documento\":{\"numero\":\"29222004000\",\"valido\":true,\"tipoDocumento\":\"CPF\"},\"dataDeNascimento\":{\"data\":[1988,10,21]}},\"enderecos\":[{\"cep\":\"13940-970\",\"logradouro\":\"Avenida Brasil 160\",\"numero\":\"607\",\"estado\":\"SAO_PAULO\",\"complemento\":null,\"bairro\":\"Centro\",\"municipio\":\"Águas de Lindóia\", \"tipoEndereco\":\"RESIDENCIAL\"}],\"telefone\":{\"ddd\":\"19\",\"numero\":\"2901-7197\"},\"email\":{\"email\":\"thomascauajorgebarbosa-98@agnet.com.br\"},\"renda\":{\"valor\":2000},\"valorDepositoAbertura\":50, " +
            "\"agenciaBancaria\": { \"id\":1}}";

        AgenciaBancaria agencia = AgenciaBancaria.builder()
            .digito(1)
            .numero(123)
            .id(1L)
            .banco(Banco.builder()
                        .cnpj(new Cnpj("12345678912345"))
                        .codigo("1")
                        .nomeFantasia(new NomeFantasia("Banco VR"))
                        .build())
            .build();

        when(agenciaBancariaService.findBy(Mockito.anyLong())).thenReturn(agencia);
        Mockito.doNothing().when(contaService).abrir(Mockito.any(ContaCorrente.class));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/conta/v1")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deveRetornarStatus_422_paraValorAberturaMenorQue50() throws Exception {

        String payload = "{\"prospect\":{\"nome\":{\"primeiroNome\":\"Emerson\",\"sobrenome\":\"Haraguchi\",\"nomeCompleto\":\"Emerson Haraguchi\"}," + 
            "\"documento\":{\"numero\":\"29222004000\",\"valido\":true,\"tipoDocumento\":\"CPF\"},\"dataDeNascimento\":{\"data\":[1988,10,21]}}," + 
            "\"enderecos\":[{\"cep\":\"13940-970\",\"logradouro\":\"Avenida Brasil 160\",\"numero\":\"607\",\"estado\":\"SAO_PAULO\",\"complemento\":null,\"bairro\":\"Centro\",\"municipio\":\"Águas de Lindóia\", \"tipoEndereco\":\"RESIDENCIAL\"}],\"telefone\":{\"ddd\":\"19\",\"numero\":\"2901-7197\"},\"email\":{\"email\":\"thomascauajorgebarbosa-98@agnet.com.br\"},\"renda\":{\"valor\":2000},\"valorDepositoAbertura\":49.99," +
            "\"agenciaBancaria\": { \"id\":1}}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/conta/v1")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    void deveRetornarStatus_400_paraPessoaNuloNoFormularioAbertura() throws Exception {

        String payload = "{\"documento\":{\"numero\":\"29222004000\",\"valido\":true,\"tipoDocumento\":\"CPF\"},\"dataDeNascimento\":{\"data\":[1988,10,21]}}," + 
            "\"enderecos\":[{\"cep\":\"13940-970\",\"logradouro\":\"Avenida Brasil 160\",\"numero\":\"607\",\"estado\":\"SAO_PAULO\",\"complemento\":null,\"bairro\":\"Centro\",\"municipio\":\"Águas de Lindóia\", \"tipoEndereco\":\"RESIDENCIAL\"}],\"telefone\":{\"ddd\":\"19\",\"numero\":\"2901-7197\"},\"email\":{\"email\":\"thomascauajorgebarbosa-98@agnet.com.br\"},\"renda\":{\"valor\":2000},\"valorDepositoAbertura\":50," +
            "\"agenciaBancaria\": { \"id\":1}}";
            
        this.mockMvc.perform(MockMvcRequestBuilders.post("/conta/v1")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void deveRetornarStatus_400_paraDocumentoNuloNoFormularioAbertura() throws Exception {

        String payload = "{\"prospect\":{\"nome\":{\"primeiroNome\":\"Emerson\",\"sobrenome\":\"Haraguchi\",\"nomeCompleto\":\"Emerson Haraguchi\"}," + 
        "\"dataDeNascimento\":{\"data\":[1988,10,21]}}," + 
        "\"enderecos\":[{\"cep\":\"13940-970\",\"logradouro\":\"Avenida Brasil 160\",\"numero\":\"607\",\"estado\":\"SAO_PAULO\",\"complemento\":null,\"bairro\":\"Centro\",\"municipio\":\"Águas de Lindóia\", \"tipoEndereco\":\"RESIDENCIAL\"}],\"telefone\":{\"ddd\":\"19\",\"numero\":\"2901-7197\"},\"email\":{\"email\":\"thomascauajorgebarbosa-98@agnet.com.br\"},\"renda\":{\"valor\":2000},\"valorDepositoAbertura\":50" +
        "\"agenciaBancaria\": { \"id\":1}}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/conta/v1")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
