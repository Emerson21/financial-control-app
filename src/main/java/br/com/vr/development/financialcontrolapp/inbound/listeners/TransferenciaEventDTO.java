package br.com.vr.development.financialcontrolapp.inbound.listeners;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
public class TransferenciaEventDTO {

    @Getter
    @JsonProperty("correlation_id")
    private String correlationId;

}
