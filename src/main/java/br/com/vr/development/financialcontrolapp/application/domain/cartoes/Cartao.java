package br.com.vr.development.financialcontrolapp.application.domain.cartoes;

import java.time.LocalDate;

import br.com.vr.development.financialcontrolapp.application.domain.ContaBancaria;
import br.com.vr.development.financialcontrolapp.application.enums.Bandeira;
import br.com.vr.development.financialcontrolapp.application.enums.FuncaoCartao;
import lombok.Getter;

@Getter
public abstract class Cartao {

    private Bandeira bandeira;
    private String numero;
    private LocalDate validade;
    private int cvv;
    private String nomeDoCorrentista;
    private ContaBancaria contaVinculada;

    public abstract FuncaoCartao getFuncao(); 


}
