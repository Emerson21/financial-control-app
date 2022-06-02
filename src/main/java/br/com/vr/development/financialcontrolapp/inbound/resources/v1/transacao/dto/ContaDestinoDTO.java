package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Embeddable
public class ContaDestinoDTO {

    @Embedded
    private CpfDTO cpf;

    @Embedded
    private DadosConta dadosConta;

    @Column
    private String nomeCorrentista;

    public String banco() {
        return dadosConta.getBanco();
    }

    public Cpf getCpfModel() {
        return new Cpf(cpf.getValue());
    }

}
