package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import br.com.vr.development.financialcontrolapp.application.domain.model.conta.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class Transacao {

    private String cpf;

    private Valor valor;

    private TipoTransferencia tipo;

    private ContaDestinoDTO conta;

    public ContaDestino toModel() {
        return null;
    }

    public br.com.vr.development.financialcontrolapp.application.domain.model.Valor toValorModel() {
        return new br.com.vr.development.financialcontrolapp.application.domain.model.Valor(valor.getValue());
    }

    public Cpf getCpfModel() {
        return new Cpf(cpf);
    }
}
