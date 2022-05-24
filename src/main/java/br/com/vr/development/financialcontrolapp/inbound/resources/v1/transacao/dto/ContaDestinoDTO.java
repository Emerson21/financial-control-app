package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto;

import br.com.vr.development.financialcontrolapp.application.domain.model.Cpf;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class ContaDestinoDTO {

    private CpfDTO cpf;

    private DadosConta dadosConta;

    private String nomeCorrentista;

    public String banco() {
        return dadosConta.getBanco();
    }

    public Cpf getCpfModel() {
        return new Cpf(cpf.getValue());
    }

}
