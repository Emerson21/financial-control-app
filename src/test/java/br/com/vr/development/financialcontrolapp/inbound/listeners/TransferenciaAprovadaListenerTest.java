package br.com.vr.development.financialcontrolapp.inbound.listeners;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaAprovadaEvent;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaAprovadaEventRepository;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransacaoMessageDTO;
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
public class TransferenciaAprovadaListenerTest {

    @SpyBean
    private TransferenciaAprovadaListener listener;

    @MockBean
    private TransferenciaAprovadaEventRepository eventRepository;

    @Autowired
    KafkaTemplate<String, TransferenciaAprovadaEvent> aprovadaTemplate;

    @Value("${topico.transferencia.aprovada}")
    private String topicoTransferenciaAprovada;

    @Captor
    private ArgumentCaptor<TransferenciaAprovadaEvent> transferenciaAprovadaCaptor;


    @Test
    public void deveReceberUmEventoDeTransferenciaAprovada() {
        UUID uuid = UUID.randomUUID();
        ContaCorrente contaCorrente = ContaCorrenteFixture.create();

        TransacaoMessageDTO transacaoMessage =
                new TransacaoMessageDTO(
                    uuid,
                    new TransacaoMessageDTO.Valor(new BigDecimal("50")),
                    contaCorrente.toContaOrigemDTO(),
                    contaCorrente.toContaDestinoDTO(),
                    TipoTransferencia.PIX.name()
                );

        TransferenciaAprovadaEvent evento = new TransferenciaAprovadaEvent(uuid, transacaoMessage);

        when(eventRepository.findByCorrelationId(anyString())).thenReturn(Optional.empty());

        aprovadaTemplate.send(topicoTransferenciaAprovada, evento);

        verify(listener, timeout(5000)).consumir(transferenciaAprovadaCaptor.capture());
        assertThat(transferenciaAprovadaCaptor.getValue()).isEqualTo(evento);

        verify(eventRepository, times(1)).findByCorrelationId(anyString());
        verify(eventRepository, times(1)).save(any(TransferenciaAprovadaEvent.class));
    }


}
