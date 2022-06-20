package br.com.vr.development.financialcontrolapp.inbound.eventhandlers;

import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.common.dtos.LancamentoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public class TransacoesEventHandler {

    @JsonProperty("id")
    private String id;

    @JsonProperty("nomeDoEvento")
    private String nomeDoEvento;

    @Getter
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("dataHoraDoEvento")
    private LocalDateTime dataHoraDoEvento;

    @JsonProperty("valor")
    private Valor valor;

    @Getter
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("lancamentos")
    private List<LancamentoDTO> lancamentos;

    @Getter
    @JsonProperty("tipoTransferencia")
    private TipoTransferencia tipoTransferencia;

}
