package br.com.vr.development.financialcontrolapp.common.dtos;

import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.enums.TipoLancamento;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import br.com.vr.development.financialcontrolapp.inbound.resources.v1.transacao.dto.ValorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class LancamentoDTO {

    private TipoLancamento tipoLancamento;
    private Descricao descricao;

    @Getter
    private TipoTransferencia tipoTransferencia;

    @Getter
    private ValorDTO valor;

}
