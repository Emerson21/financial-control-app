package br.com.vr.development.financialcontrolapp.application.domain.model.events.transacoes;

import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.domain.model.events.TransferenciaEvent;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.common.dtos.LancamentoDTO;

import java.util.List;
import java.util.UUID;

public class TransferenciaAprovada extends TransferenciaEvent {

    private static final String NOME_EVENTO = "TransferenciaAprovada";

    public TransferenciaAprovada(UUID correlationId, Valor valor, List<LancamentoDTO> lancamentos) {
        super(correlationId, NOME_EVENTO, valor, lancamentos, TipoTransferencia.ESTORNO_PAGAMENTO);
        LancamentoDTO lancamentoDTO = new LancamentoDTO(TipoLancamento.CREDITO, new Descricao("Transferencia Aprovada"), TipoTransferencia.DEPOSITO, valor.toValorDTO());
        this.lancamentos.add(lancamentoDTO);
    }

}
