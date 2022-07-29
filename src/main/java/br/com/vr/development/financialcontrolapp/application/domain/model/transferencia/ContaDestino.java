package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaExterna;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Poupanca;
import br.com.vr.development.financialcontrolapp.application.domain.model.lancamento.Lancamento;
import br.com.vr.development.financialcontrolapp.infrastructure.repository.data.model.TransacaoMessageDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Set;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = ContaCorrente.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContaCorrente.class, name = "contaCorrente"),
        @JsonSubTypes.Type(value = Poupanca.class, name = "poupanca"),
        @JsonSubTypes.Type(value = ContaExterna.class, name = "contaExterna")
})
public interface ContaDestino extends Serializable {
    Set<Lancamento> getLancamentos();

    TransacaoMessageDTO.ContaDestino toContaDestinoDTO();
}
