package br.com.vr.development.financialcontrolapp.application.domain.model.events;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.transacoes.TransferenciaAprovada;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.transacoes.TransferenciaReprovada;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.common.dtos.LancamentoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@ToString
@Document("transferencias_event")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferenciaEvent {

    @Id
    private String id;

    @Getter
    @JsonProperty("correlation_id")
    @Field(value = "correlationId", targetType = FieldType.STRING)
    private String correlationId;

    @Field(name = "event_name", targetType = FieldType.STRING)
    protected String nomeDoEvento;

    @Field(name = "data_hora_evento", targetType = FieldType.DATE_TIME)
    private LocalDateTime dataHoraDoEvento;

    @Field(name = "valor")
    protected Valor valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Field(targetType = FieldType.ARRAY)
    protected List<LancamentoDTO> lancamentos;

    @Field(name = "tipo_transferencia", targetType = FieldType.STRING)
    protected TipoTransferencia tipoTransferencia;

    public TransferenciaEvent(UUID correlationId, String nomeDoEvento, Valor valor, List<LancamentoDTO> lancamentos, TipoTransferencia tipoTransferencia) {
        this.id = correlationId.toString();
        this.correlationId = correlationId.toString();
        this.nomeDoEvento = nomeDoEvento;
        this.dataHoraDoEvento = LocalDateTime.now();
        this.valor = valor;
        this.lancamentos = lancamentos;
        this.tipoTransferencia = tipoTransferencia;
    }

    public TransferenciaEvent(UUID correlationId, String nomeDoEvento, Valor valor, Set<Lancamento> lancamentos, TipoTransferencia tipoTransferencia) {
        this(correlationId, nomeDoEvento, valor, lancamentos.stream().map(lancamento ->
                        new LancamentoDTO(
                                lancamento.getTipoLancamento(),
                                lancamento.getDescricao(),
                                lancamento.getTipoTransferencia(),
                                lancamento.getValor().toValorDTO()))
                .collect(Collectors.toList()),
                tipoTransferencia);
    }

    @JsonIgnore
    public boolean isInterna() {
        return nomeDoEvento.toLowerCase().contains("interna");
    }

    public TransferenciaReprovada toTransferenciaReprovada() {
        return new TransferenciaReprovada(UUID.fromString(correlationId), valor, lancamentos);
    }

    public TransferenciaAprovada toTransferenciaAprovada() {
        return new TransferenciaAprovada(UUID.fromString(correlationId), valor, lancamentos);
    }
}
