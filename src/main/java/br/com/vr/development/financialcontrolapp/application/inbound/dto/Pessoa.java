package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import java.io.Serializable;

import br.com.vr.development.financialcontrolapp.application.commons.Cpf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Pessoa implements Serializable {
 
    private Nome nome;
    private Cpf documento;
    private DataNascimento dataDeNascimento;

}
