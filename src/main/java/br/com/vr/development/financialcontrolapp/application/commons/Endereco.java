package br.com.vr.development.financialcontrolapp.application.commons;

import java.io.Serializable;

import br.com.vr.development.financialcontrolapp.application.enums.TipoEndereco;
import br.com.vr.development.financialcontrolapp.application.enums.UF;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Endereco implements Serializable {

    private String cep;
    private String logradouro;
    private String numero;
    private UF estado;
    private String complemento; 
    private String bairro;
    private String cidade;

    abstract TipoEndereco getTipoEndereco();

}
