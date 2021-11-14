package br.com.vr.development.financialcontrolapp.application.domain;

import br.com.vr.development.financialcontrolapp.application.commons.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.enums.TipoContaBancaria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ContaCorrente implements ContaBancaria {

    private AgenciaBancaria agencia;
    private Long numero;
    private Long digito;

    @Override
    public TipoContaBancaria getTipoContaBancaria() {
        return TipoContaBancaria.CONTA_CORRENTE;
    }
    
}
