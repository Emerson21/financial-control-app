package br.com.vr.development.financialcontrolapp.inbound.listeners;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.messages.TransacaoMessage;
import br.com.vr.development.financialcontrolapp.application.domain.service.MessageSender;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.fixtures.ContaCorrenteFixture;
import br.com.vr.development.financialcontrolapp.inbound.listeners.events.TransferenciaRecebidaEvent;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ValorDTO;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.TransferenciaRecebidaEventRepository;
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
public class TransferenciaRecebidaListenerTest {

    @SpyBean
    private TransferenciaRecebidaListener listener;

    @MockBean
    private TransferenciaRecebidaEventRepository eventRepository;

    @Autowired
    KafkaTemplate<String, TransferenciaRecebidaEvent> recebidaTemplate;

    @Value("${topico.transferencia.recebida}")
    private String topicoTransferenciaRecebida;

    @Captor
    private ArgumentCaptor<TransferenciaRecebidaEvent> transferenciaRecebidaCaptor;


    @Test
    public void deveReceberUmEventoDeTransferenciaRecebida() {
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

        TransferenciaRecebidaEvent evento = new TransferenciaRecebidaEvent(uuid, transacaoMessage);

        when(eventRepository.findByCorrelationId(anyString())).thenReturn(Optional.empty());

        recebidaTemplate.send(topicoTransferenciaRecebida, evento);

        verify(listener, timeout(5000)).consumir(transferenciaRecebidaCaptor.capture());
        assertThat(transferenciaRecebidaCaptor.getValue()).isEqualTo(evento);

        verify(eventRepository, times(1)).save(any(TransferenciaRecebidaEvent.class));
        verify(eventRepository, times(1)).findByCorrelationId(anyString());
    }


}
