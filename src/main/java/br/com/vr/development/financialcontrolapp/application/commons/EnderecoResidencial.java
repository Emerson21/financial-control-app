package br.com.vr.development.financialcontrolapp.application.commons;

import br.com.vr.development.financialcontrolapp.application.enums.TipoEndereco;

public class EnderecoResidencial extends Endereco {

    @Override
    TipoEndereco getTipoEndereco() {
        return TipoEndereco.RESIDENCIAL;
    }
    
}
