package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import java.io.Serializable;

import br.com.vr.development.financialcontrolapp.application.commons.Celular;
import br.com.vr.development.financialcontrolapp.application.commons.Email;
import br.com.vr.development.financialcontrolapp.application.commons.EnderecoResidencial;
import br.com.vr.development.financialcontrolapp.application.domain.Renda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormularioAberturaConta implements Serializable {
    
    private Pessoa prospect;
    private EnderecoResidencial endereco;
    private Celular telefone;
    private Email email;
    private Renda renda;

}
