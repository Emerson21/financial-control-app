package br.com.vr.development.financialcontrolapp.inbound.listeners;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaRecebidaEvent;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaReprovadaEvent;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ValorDTO;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaRecebidaEventRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaReprovadaEventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DirtiesContext
@ExtendWith(MockitoExtension.class)
@EmbeddedKafka
@SpringBootTest(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
public class TransferenciaReprovadaListenerTest {

    @SpyBean
    private TransferenciaReprovadaListener listener;

    @MockBean
    private TransferenciaReprovadaEventRepository eventRepository;

    @Autowired
    KafkaTemplate<String, TransferenciaReprovadaEvent> reprovadaTemplate;

    @Value("${topico.transferencia.reprovada}")
    private String topicoTransferenciaReprovada;

    @Captor
    private ArgumentCaptor<TransferenciaReprovadaEvent> transferenciaReprovadaCaptor;


    @Test
    public void deveReceberUmEventoDeTransferenciaReprovada() {
        UUID uuid = UUID.randomUUID();
        ContaCorrente contaCorrente = ContaCorrenteFixture.create();

        TransacaoMessage transacaoMessage =
                new TransacaoMessage(
                    uuid,
                    new ValorDTO(new BigDecimal("-50")),
                    contaCorrente,
                    contaCorrente,
                    TipoTransferencia.PIX
                );

        TransferenciaReprovadaEvent evento = new TransferenciaReprovadaEvent(uuid, transacaoMessage);

        when(eventRepository.findByCorrelationId(anyString())).thenReturn(Optional.empty());

        reprovadaTemplate.send(topicoTransferenciaReprovada, evento);

        verify(listener, timeout(5000)).consumir(transferenciaReprovadaCaptor.capture());
        assertThat(transferenciaReprovadaCaptor.getValue()).isEqualTo(evento);

        verify(eventRepository, times(1)).save(any(TransferenciaReprovadaEvent.class));
        verify(eventRepository, times(1)).findByCorrelationId(anyString());
    }


}
