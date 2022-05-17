package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.Poupanca;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = ContaCorrente.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContaCorrente.class, name = "contaCorrente"),
        @JsonSubTypes.Type(value = Poupanca.class, name = "poupanca")
})
public interface ContaDestino {

    Valor getSaldo();

    void deposita(Valor valor, TipoTransferencia tipoTransferencia);
    
}
