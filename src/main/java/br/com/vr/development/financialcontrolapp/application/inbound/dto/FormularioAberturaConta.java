package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private Pessoa prospect;
    
    @NotNull
    private EnderecoResidencial endereco;
    
    @NotNull
    private Celular telefone;
    
    @NotNull
    private Email email;

    @NotNull
    private Renda renda;

    @NotNull
    @DecimalMin(value = "50.00")
    private BigDecimal valorDepositoAbertura;

}
