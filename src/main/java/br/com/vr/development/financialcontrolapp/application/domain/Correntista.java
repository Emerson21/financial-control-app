package br.com.vr.development.financialcontrolapp.application.domain;

import br.com.vr.development.financialcontrolapp.application.commons.Celular;
import br.com.vr.development.financialcontrolapp.application.commons.Cpf;
import br.com.vr.development.financialcontrolapp.application.commons.Email;
import br.com.vr.development.financialcontrolapp.application.commons.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Correntista {

    private Nome nome;
    private Cpf cpf;
    private Endereco endereco;
    private DataNascimento dataNascimento;
    private RendaMensal rendaMensal;
    private Email email;
    private Celular celular;

}
