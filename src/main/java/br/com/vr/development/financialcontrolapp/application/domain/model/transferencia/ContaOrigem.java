package br.com.vr.development.financialcontrolapp.application.domain.model.transferencia;

import br.com.vr.development.financialcontrolapp.application.domain.model.Descricao;
import br.com.vr.development.financialcontrolapp.application.domain.model.Valor;
import br.com.vr.development.financialcontrolapp.application.enums.TipoTransferencia;

import java.time.LocalDateTime;

public interface ContaOrigem {

    Valor getSaldo();

    void saque(Valor valor, TipoTransferencia tipoTransferencia);

}
