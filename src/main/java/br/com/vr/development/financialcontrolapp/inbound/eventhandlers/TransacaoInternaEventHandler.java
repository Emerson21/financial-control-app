package br.com.vr.development.financialcontrolapp.inbound.eventhandlers;

import br.com.vr.development.financialcontrolapp.application.domain.model.elasticsearch.ElasticSearchModel;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ValorDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Document(indexName = "transacoes_internas")
public class TransacaoInternaEventHandler extends ElasticSearchModel {

    @Field(name = "event_name", type = FieldType.Text)
    private String nomeDoEvento;

    @Field(name = "data_hora_evento", type = FieldType.Date_Nanos)
    private LocalDateTime dataHoraDoEvento;

    @Field(name = "valor_total", type = FieldType.Nested, includeInParent = true)
    private ValorDTO valorTotal;

    @Field(name = "total_de_lancamentos", type = FieldType.Text)
    private Long totalLancamentos;

    @Field(name = "tipo_transferencia", type = FieldType.Text)
    private TipoTransferencia tipoTransferencia;


    public TransacaoInternaEventHandler(TransacoesEventHandler transacaoMessage) {
        super.id = UUID.randomUUID().toString();
        this.nomeDoEvento = "TransacaoInterna";
        this.dataHoraDoEvento = transacaoMessage.getDataHoraDoEvento();
        this.valorTotal = new ValorDTO(transacaoMessage.getLancamentos()
                .stream()
                .map(lancamentoDTO -> lancamentoDTO.getValor().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        this.totalLancamentos = Long.valueOf(transacaoMessage.getLancamentos().size());
        this.tipoTransferencia = transacaoMessage.getTipoTransferencia();
    }
}
