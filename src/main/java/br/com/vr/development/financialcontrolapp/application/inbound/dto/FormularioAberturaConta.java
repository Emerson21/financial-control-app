package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import br.com.vr.development.financialcontrolapp.application.commons.Email;
import br.com.vr.development.financialcontrolapp.application.commons.Endereco;
import br.com.vr.development.financialcontrolapp.application.commons.Telefone;
import br.com.vr.development.financialcontrolapp.application.domain.Renda;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class FormularioAberturaConta {
    
    private final Pessoa prospect;
    private final Endereco endereco;
    private final Telefone telefone;
    private final Email email;
    private final Renda renda;

}
