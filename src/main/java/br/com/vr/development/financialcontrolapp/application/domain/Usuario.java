package br.com.vr.development.financialcontrolapp.application.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.vr.development.financialcontrolapp.application.commons.Documento;
import br.com.vr.development.financialcontrolapp.application.commons.Email;
import br.com.vr.development.financialcontrolapp.application.commons.Endereco;
import br.com.vr.development.financialcontrolapp.application.commons.Telefone;
import lombok.Data;

@Data
public class Usuario {

    private String nomeCompleto;
    private Documento documento;
    private Endereco endereco;
    private LocalDate dataDeNascimento;
    private BigDecimal rendaMensal;
    private Email email;
    private Telefone celular;
    
}
