package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.service.transacoes.TransacoesService;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ContaDestinoDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferenciaComposite {

    private TransacoesService transferenciaInterna;
    private TransacoesService transferenciaExterna;

    /*
        Possíveis valores para qualifier para as transferencias externas

     @Qualifier("transferenciaExterna") TransacoesService transferenciaExterna
     @Qualifier("transferenciaExternaMS") TransacoesService transferenciaExterna
     @Qualifier("transferenciaExternaOutbox") TransacoesService transferenciaExterna

     */

    @Autowired
    public TransferenciaComposite(@Qualifier("transferenciaInterna") TransacoesService transferenciaInterna,
                                  @Qualifier("transferenciaExternaOutbox") TransacoesService transferenciaExterna) {
        this.transferenciaInterna = transferenciaInterna;
        this.transferenciaExterna = transferenciaExterna;
    }

    public TransacoesService selecionarTransferencia(ContaDestinoDTO conta, Banco banco) {
        return conta.banco().equals(banco.getCodigo())
                ? transferenciaInterna
                : transferenciaExterna;
    }
}
