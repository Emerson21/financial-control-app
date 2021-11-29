package br.com.vr.development.financialcontrolapp.application.domain;

import java.time.LocalDate;
import java.util.List;

import br.com.vr.development.financialcontrolapp.application.commons.Documento;
import br.com.vr.development.financialcontrolapp.application.commons.Email;
import br.com.vr.development.financialcontrolapp.application.commons.Endereco;
import br.com.vr.development.financialcontrolapp.application.commons.Telefone;
import br.com.vr.development.financialcontrolapp.application.domain.cartoes.Cartao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Correntista {

    private String nomeCompleto;
    private Documento documento;
    private Endereco endereco;
    private LocalDate dataDeNascimento;
    private Renda rendaMensal;
    private Email email;
    private Telefone celular;
    private ContaBancaria contaBancaria;
    private List<Cartao> cartoes;

}
