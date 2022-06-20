package br.com.vr.development.financialcontrolapp.application.domain.model.events;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.common.dtos.LancamentoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@ToString
@Document("transferencias_event")
public class TransferenciaEvent {

    @Id
    private String id;

    @Field(name = "event_name", type = FieldType.Text)
    private String nomeDoEvento;

    @Field(name = "data_hora_evento", type = FieldType.Date_Nanos)
    private LocalDateTime dataHoraDoEvento;

    @Field(name = "valor", type = FieldType.Nested, includeInParent = true)
    private Valor valor;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<LancamentoDTO> lancamentos;

    @Field(name = "tipo_transferencia", type = FieldType.Text)
    private TipoTransferencia tipoTransferencia;

    public TransferenciaEvent(String nomeDoEvento, Valor valor, Set<Lancamento> lancamentos, TipoTransferencia tipoTransferencia) {
        this.id = UUID.randomUUID().toString();
        this.nomeDoEvento = nomeDoEvento;
        this.dataHoraDoEvento = LocalDateTime.now();
        this.valor = valor;
        this.lancamentos = lancamentos.stream().map(lancamento ->
                new LancamentoDTO(
                        lancamento.getTipoLancamento(),
                        lancamento.getDescricao(),
                        lancamento.getTipoTransferencia(),
                        lancamento.getValor().toValorDTO()))
                .collect(Collectors.toList());
        this.tipoTransferencia = tipoTransferencia;
    }

    @JsonIgnore
    public boolean isInterna() {
        return "interna".equalsIgnoreCase(nomeDoEvento.toLowerCase());
    }
}
