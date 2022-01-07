package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.DadosBancarios;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Transferencia {

    private Valor valor;
    private TipoTransferencia tipoTransferencia;
    private DadosBancarios dadosBancarios;

}
