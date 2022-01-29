package br.com.vr.development.financialcontrolapp.fixtures;

import br.com.vr.development.financialcontrolapp.application.domain.model.Endereco;
import br.com.vr.development.financialcontrolapp.application.enums.TipoEndereco;
import br.com.vr.development.financialcontrolapp.application.enums.UF;

public class EnderecoFixture {

    public static Endereco create() {
        Endereco endereco = new Endereco();
        endereco.setCep("13940-970");
        endereco.setBairro("Centro");
        endereco.setMunicipio("Águas de Lindóia");
        endereco.setEstado(UF.SAO_PAULO);
        endereco.setLogradouro("Avenida Brasil 160");
        endereco.setNumero("607");
        endereco.setTipoEndereco(TipoEndereco.RESIDENCIAL);
        return endereco;
    }

}
