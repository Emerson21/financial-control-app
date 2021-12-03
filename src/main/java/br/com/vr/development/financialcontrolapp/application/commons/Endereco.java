package br.com.vr.development.financialcontrolapp.application.commons;

import br.com.vr.development.financialcontrolapp.application.enums.TipoEndereco;
import br.com.vr.development.financialcontrolapp.application.enums.UF;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Endereco {

    private String cep;
    private String logradouro;
    private String numero;
    private UF estado;
    private String complemento; 
    private String bairro;
    private String municipio;
    private TipoEndereco tipoEndereco;
}
