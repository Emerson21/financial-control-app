package br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao;

import br.com.vr.development.financialcontrolapp.application.domain.model.Cartao;
import br.com.vr.development.financialcontrolapp.application.domain.model.cartoes.CartaoDeDebito;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaDestino;
import br.com.vr.development.financialcontrolapp.application.domain.model.transferencia.ContaOrigem;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class TransacaoDTO {

    @JsonAlias("cartao")
    private Cartao cartao;

    @JsonAlias("contaOrigem")
    private ContaOrigem contaOrigem;

    @JsonAlias("contaDestino")
    private ContaDestino contaDestino;

}
