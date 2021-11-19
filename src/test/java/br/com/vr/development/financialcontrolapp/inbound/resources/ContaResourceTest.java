package br.com.vr.development.financialcontrolapp.inbound.resources;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.vr.development.financialcontrolapp.application.inbound.ContaResource;
import br.com.vr.development.financialcontrolapp.exception.BancoExceptionHandler;

@WebMvcTest
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
    public void deveReceberDadosParaAberturaDaContaCorrente() {
        
        String uri = "/v1/banco/conta";




    }


}
