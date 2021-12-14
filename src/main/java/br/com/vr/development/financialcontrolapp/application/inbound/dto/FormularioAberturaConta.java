package br.com.vr.development.financialcontrolapp.application.inbound.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.vr.development.financialcontrolapp.application.domain.AgenciaBancaria;
import br.com.vr.development.financialcontrolapp.application.domain.Banco;
import br.com.vr.development.financialcontrolapp.application.domain.Celular;
import br.com.vr.development.financialcontrolapp.application.domain.Cnpj;
import br.com.vr.development.financialcontrolapp.application.domain.ContaCorrente;
import br.com.vr.development.financialcontrolapp.application.domain.Correntista;
import br.com.vr.development.financialcontrolapp.application.domain.Email;
import br.com.vr.development.financialcontrolapp.application.domain.Endereco;
import br.com.vr.development.financialcontrolapp.application.domain.NomeFantasia;
import br.com.vr.development.financialcontrolapp.application.domain.Pessoa;
import br.com.vr.development.financialcontrolapp.application.domain.RendaMensal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormularioAberturaConta {

    @NotNull
    @Valid
    private Pessoa prospect;
    
    @NotNull
    @Valid
    private List<Endereco> enderecos;
    
    @NotNull
    @Valid
    private Celular telefone;
    
    @NotNull
    @Valid
    private Email email;

    @NotNull
    @Valid
    private RendaMensal renda;

    @NotNull
    private BigDecimal valorDepositoAbertura;

    // public boolean isValorDepositoPermitido(BigDecimal valorMinimoPermitido) {
    //     return this.valorDepositoAbertura.compareTo(valorMinimoPermitido) < 0;
    // }

    public ContaCorrente toContaCorrente() {

        Banco banco =  Banco.builder()
            .cnpj(new Cnpj("42500796000191"))
            .codigo("077")
            .nomeFantasia(new NomeFantasia("INTER"))
            .build();

        AgenciaBancaria agencia = AgenciaBancaria.builder()
            .banco(banco)
            .numero(1)
            .digito(1).build();
        
        Correntista correntista = Correntista.builder()
            .nome(this.prospect.getNome())
            .email(this.email)
            .enderecos(this.enderecos)
            .cpf(this.prospect.getDocumento())
            .dataNascimento(this.prospect.getDataDeNascimento())
            .celular(this.getTelefone())
            .rendaMensal(this.getRenda())
            .build();

        return new ContaCorrente(agencia, correntista);
    }

}
