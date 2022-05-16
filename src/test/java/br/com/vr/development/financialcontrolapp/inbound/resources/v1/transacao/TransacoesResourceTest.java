package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao;

import br.com.vr.development.financialcontrolapp.application.domain.model.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.Transferencia;
import br.com.vr.development.financialcontrolapp.exception.ContaNotFoundException;
import br.com.vr.development.financialcontrolapp.exception.FinancialExceptionHandler;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.*;
import br.com.vr.development.financialcontrolapp.repository.ContaRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia.TED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class TransacoesResourceTest {

    private static final String URI = "/transacoes";
    private static final String CONTENT_TYPE_TRANSACAO = "application/vnd.transacoes.v1+json";

    private MockMvc mockMvc;

    @InjectMocks
    private TransacaoResource transacaoResource;

    @Mock
    private TransacoesService transacoesService;

    @Mock
    private ContaRepository contaRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
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
        DadosConta dadosConta = new DadosConta("077", "0987", "3", "123456789", "0");
        ContaDestino contaDestino = new ContaDestino(new CpfDTO("46133685026"), dadosConta);
        Transacao transacao = new Transacao("54173913010", new Valor(new BigDecimal("50")), TED, contaDestino);

        ContaCorrente contaCorrente = ContaCorrenteFixture.create();

        String payload = objectMapper
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .writeValueAsString(transacao);

        doNothing().when(transacoesService).transacionar(any(Transferencia.class));
        when(contaRepository.findBy(any(Cpf.class))).thenReturn(Optional.of(contaCorrente));

        mockMvc.perform(
            MockMvcRequestBuilders.post(URI)
                    .content(payload)
                    .contentType(CONTENT_TYPE_TRANSACAO))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("mensagem").value("Transação realizada com sucesso."));

        verify(contaRepository, times(1)).findBy(any(Cpf.class));
        verify(transacoesService, times(1)).transacionar(any(Transferencia.class));
    }

    @Test
    void deveLancarContaNotFoundExceptionQuandoNaoEncontrarContaCadastrada() throws Exception {
        DadosConta dadosConta = new DadosConta("077", "0987", "3", "123456789", "0");
        ContaDestino contaDestino = new ContaDestino(new CpfDTO("46133685026"), dadosConta);
        Transacao transacao = new Transacao("54173913010", new Valor(new BigDecimal("50")), TED, contaDestino);

        String payload = objectMapper
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .writeValueAsString(transacao);

        ContaNotFoundException contaNotFoundException = new ContaNotFoundException();
        when(contaRepository.findBy(any(Cpf.class))).thenThrow(contaNotFoundException);

        mockMvc.perform(MockMvcRequestBuilders.post(URI)
                            .content(payload)
                            .contentType(CONTENT_TYPE_TRANSACAO))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.jsonPath("codigo").value("-4"))
                .andExpect(MockMvcResultMatchers.jsonPath("mensagem").value("Conta nao encontrada"));

    }

}
