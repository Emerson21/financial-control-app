package br.com.vr.development.financialcontrolapp.inbound.eventhandlers;

import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.common.dtos.LancamentoDTO;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ValorDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Document(indexName = "transacoes_externas")
public class TransacaoExternaEventHandler {

    @Id
    protected String id;

    @Field(name = "event_name", type = FieldType.Text)
    private String nomeDoEvento;

    @Field(name = "data_hora_evento", type = FieldType.Date_Nanos)
    private LocalDateTime dataHoraDoEvento;

    @Field(name = "valor_total_por_tipo_transferenca", type = FieldType.Nested, includeInParent = true)
    private Map<TipoTransferencia, ValorDTO> valorPorTipoTransferencia;


    public TransacaoExternaEventHandler(TransacoesEventHandler transacaoMessage) {
        this.id = UUID.randomUUID().toString();
        this.nomeDoEvento = "TransacaoExterna";
        this.dataHoraDoEvento = transacaoMessage.getDataHoraDoEvento();
        this.valorPorTipoTransferencia =
            transacaoMessage.getLancamentos()
                .stream()
                .collect(
                    Collectors.groupingBy(
                        LancamentoDTO::getTipoTransferencia,
                        Collectors.reducing(
                            ValorDTO.ZERO.ZERO,
                            LancamentoDTO::getValor,
                            ValorDTO::somaIgnorandoSinal))
                );
    }
}
