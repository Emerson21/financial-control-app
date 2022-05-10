package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao;

import br.com.vr.development.financialcontrolapp.application.domain.model.Cartao;
import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeDebito;
import br.com.vr.development.financialcontrolapp.exception.FinancialExceptionHandler;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import br.com.vr.development.financialcontrolapp.application.domain.service.TransacoesService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransacoesResourceTest {

    private static final String URI = "/transacoes/v1";

    private MockMvc mockMvc;

    @InjectMocks
    private TransacaoResource transacaoResource;

    @Mock
    private TransacoesService transacoesService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(transacaoResource)
                .setControllerAdvice(new FinancialExceptionHandler())
                .build();
        objectMapper.registerModule(new ParameterNamesModule()).registerModule(new Jdk8Module())
                          .registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void deveRealizarUmaTransacaoBancaria() throws Exception {
        ContaCorrente contaOrigem = ContaCorrenteFixture.create();
        ContaCorrente contaDestino = ContaCorrenteFixture.create();

        Cartao cartao = new CartaoDeDebito(contaOrigem);

        TransacaoDTO transacaoDTO = new TransacaoDTO(cartao, contaOrigem, contaDestino);
        String payload = objectMapper
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .writeValueAsString(transacaoDTO);

        doNothing().when(transacoesService).transacionar(any(TransacaoDTO.class));

        mockMvc.perform(
            MockMvcRequestBuilders.post(URI)
                    .content(payload)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
//            .andExpect(MockMvcResultMatchers.jsonPath("").value(""));

        Mockito.verify(transacoesService, times(1)).transacionar(any(TransacaoDTO.class));
    }
}
